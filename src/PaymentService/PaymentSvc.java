package PaymentService;

import java.util.*;
import PaymentService.entities.*;
import PaymentService.routing.*;

public class PaymentSvc {
    final Map<String, Transaction> transactions;
    final Map<String, Gateway> gateways;
    private final TrafficRouter router;
    private final TrafficAdjuster adjuster;

    private PaymentSvc(Builder builder) {
        this.transactions = builder.transactions;
        this.gateways = builder.gateways;
        this.router = builder.router;
        this.adjuster = builder.adjuster;
    }

    public static class Builder {
        private Map<String, Transaction> transactions;
        private Map<String, Gateway> gateways;
        private TrafficRouter router;
        private TrafficAdjuster adjuster;

        public Builder withTransactions(Map<String, Transaction> transactions) {
            this.transactions = transactions;
            return this;
        }

        public Builder withGateways(Map<String, Gateway> gateways) {
            this.gateways = gateways;
            return this;
        }

        public Builder withRouter(TrafficRouter router) {
            this.router = router;
            return this;
        }

        public Builder withAdjuster(TrafficAdjuster adjuster) {
            this.adjuster = adjuster;
            return this;
        }

        public PaymentSvc build() {
            if (this.transactions == null) {
                this.transactions = new HashMap<>();
            }
            if (this.gateways == null) {
                this.gateways = new HashMap<>();
            }
            if (this.router == null) {
                this.router = new WeightedTrafficRouter();
            }
            if (this.adjuster == null) {
                this.adjuster = new PercentageTrafficAdjuster();
            }
            return new PaymentSvc(this);
        }
    }

    public void createGateway(String gatewayName, double weight) {
        Gateway g = new Gateway(gatewayName, weight);
        gateways.put(gatewayName, g);

        List<Gateway> activeGateways = getActiveGateways();
        adjuster.adjustForNewNode(activeGateways, g);
    }

    public void initiateTransaction(String userId, String orderId) {
        Transaction t = new Transaction(orderId, userId);

        Gateway g = chooseGateway();
        this.transactions.put(t.getTransactionId(), t);
        g.addTransaction(t);
    }

    public Gateway chooseGateway() {
        List<Gateway> activeGateways = getActiveGateways();
        return router.route(activeGateways);
    }

    public void receiveTransactionStatus(String gatewayName, TransactionStatus status, String transactionId) {
        if (!transactions.containsKey(transactionId)) {
            throw new IllegalArgumentException("Transaction not found");
        }
        Transaction t = transactions.get(transactionId);
        t.setStatus(status);

        Gateway g = gateways.get(gatewayName);
        if (g != null) {
            int currentTime = (int) (System.currentTimeMillis() / 1000);
            GatewayStatus previousStatus = g.getStatus();
            g.evaluateHealth(currentTime);
            
            // Only trigger traffic adjustment if it strictly transitioned from ACTIVE to INACTIVE
            if (previousStatus == GatewayStatus.ACTIVE && g.getStatus() == GatewayStatus.INACTIVE) {
                adjustTraffic();
            }
        }
    }

    public void adjustTraffic() {
        List<Gateway> activeGateways = getActiveGateways();
        adjuster.adjustForRemovedNode(activeGateways);
    }

    private List<Gateway> getActiveGateways() {
        int currentTime = (int) (System.currentTimeMillis() / 1000);
        List<Gateway> activeGateways = new ArrayList<>();
        List<Gateway> restoredGateways = new ArrayList<>();

        for (Gateway g : gateways.values()) {
            if (g.getStatus() == GatewayStatus.ACTIVE) {
                activeGateways.add(g);
            } else if (g.getStatus() == GatewayStatus.INACTIVE) {
                if (currentTime - g.getInactiveSince() >= 30 * 60) {
                    g.setStatus(GatewayStatus.ACTIVE);
                    g.setInactiveSince(0);
                    restoredGateways.add(g);
                }
            }
        }

        for (Gateway g : restoredGateways) {
            adjuster.adjustForNewNode(activeGateways, g);
            activeGateways.add(g);
        }

        return activeGateways;
    }
}