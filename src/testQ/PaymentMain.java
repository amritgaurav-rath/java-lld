package testQ;

import testQ.entities.Gateway;
import testQ.entities.TransactionStatus;
import testQ.entities.GatewayStatus;

public class PaymentMain {
    public static void main(String[] args) {
        System.out.println("=== Initializing Payment Service ===");
        PaymentSvc paymentSvc = new PaymentSvc.Builder().build();

        System.out.println("\n--- Scenario 1: Adding Initial Gateways ---");
        paymentSvc.createGateway("Gateway_A", 60.0);
        paymentSvc.createGateway("Gateway_B", 40.0);
        printGateways(paymentSvc);

        System.out.println("\n--- Scenario 2: Adding a New Gateway (Traffic Redistribution) ---");
        paymentSvc.createGateway("Gateway_C", 40.0);
        printGateways(paymentSvc);

        System.out.println("\n--- Scenario 3: Initiating Transactions ---");
        for (int i = 1; i <= 10; i++) {
            paymentSvc.initiateTransaction("user" + i, "order" + i);
        }

        // Print how many transactions each gateway received
        for (Gateway g : paymentSvc.gateways.values()) {
            System.out.printf("%s received %d transactions.%n", g.getGatewayName(), g.getTxnsToProcess().size());
        }

        System.out.println("\n--- Scenario 4: Simulating Health Failure ---");
        // Let's fail all transactions on Gateway_C to trigger health check
        Gateway gatewayC = paymentSvc.gateways.get("Gateway_C");
        if (gatewayC != null && !gatewayC.getTxnsToProcess().isEmpty()) {
            System.out.println("Simulating failures on Gateway_C...");
            for (int i = 0; i < gatewayC.getTxnsToProcess().size(); i++) {
                String txnId = gatewayC.getTxnsToProcess().get(i).getTransactionId();
                // This triggers the passive health evaluation
                paymentSvc.receiveTransactionStatus("Gateway_C", TransactionStatus.FAILURE, txnId);
            }
        }

        System.out.println("Gateway statuses after failure simulation:");
        printGateways(paymentSvc);

        System.out.println("\n--- Scenario 5: Traffic Routing after Node Removal ---");
        // Initiate more transactions, none should go to Gateway_C
        for (int i = 11; i <= 15; i++) {
            paymentSvc.initiateTransaction("user" + i, "order" + i);
        }

        for (Gateway g : paymentSvc.gateways.values()) {
            System.out.printf("%s has %d total transactions.%n", g.getGatewayName(), g.getTxnsToProcess().size());
        }

        System.out.println("\n--- Scenario 6: Simulating Auto-Recovery (Time Travel) ---");
        // We will manually push the inactiveSince back by 35 minutes to trigger the
        // recovery
        if (gatewayC != null) {
            int timeTravelTs = (int) (System.currentTimeMillis() / 1000) - (35 * 60);
            gatewayC.setInactiveSince(timeTravelTs);
            System.out.println("Fast-forwarding time... Gateway_C inactiveSince set to 35 mins ago.");
        }

        // Triggering any traffic adjustment or routing will auto-recover it
        try {
            paymentSvc.chooseGateway();
        } catch (Exception e) {
            // ignore
        }

        System.out.println("Gateway statuses after auto-recovery check:");
        printGateways(paymentSvc);

        System.out.println("\n--- Scenario 7: No Active Gateways Available ---");
        PaymentSvc emptySvc = new PaymentSvc.Builder().build();
        try {
            emptySvc.initiateTransaction("user_stranded", "order_stranded");
        } catch (IllegalStateException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
            System.out.println("no active gateways");
        }
    }

    private static void printGateways(PaymentSvc paymentSvc) {
        for (Gateway g : paymentSvc.gateways.values()) {
            System.out.printf("Gateway: %s | Status: %s | Weight: %.2f%%%n",
                    g.getGatewayName(), g.getStatus(), g.getWeight());
        }
    }
}
