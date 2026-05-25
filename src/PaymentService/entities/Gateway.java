package PaymentService.entities;

import java.util.*;

public class Gateway {
    private String gatewayId;
    private String gatewayName;
    private double weight;
    private Double successRateInLast15mins;
    private GatewayStatus gatewayStatus;
    private int inactiveSince;
    List<Transaction> txnsToProcess;
    private int timestamp;

    public Gateway(String gatewayName, double weight) {
        this.gatewayId = UUID.randomUUID().toString();
        this.gatewayName = gatewayName;
        this.weight = weight;
        this.gatewayStatus = GatewayStatus.ACTIVE;
        this.txnsToProcess = new ArrayList<>();
        this.timestamp = (int) (System.currentTimeMillis() / 1000);
        this.inactiveSince = 0;
    }

    public void addTransaction(Transaction txn) {
        this.txnsToProcess.add(txn);
    }

    public void evaluateHealth(int currentTime) {
        int totalCompleted = 0;
        int totalSuccess = 0;

        for (int i = txnsToProcess.size() - 1; i >= 0; i--) {
            Transaction t = txnsToProcess.get(i);
            if (currentTime - t.getTimestamp() > 15 * 60) {
                break;
            }

            if (t.getStatus() != TransactionStatus.INPROGRESS) {
                totalCompleted++;
                if (t.getStatus() == TransactionStatus.SUCCESS) {
                    totalSuccess++;
                }
            }
        }

        if (totalCompleted > 0) {
            this.successRateInLast15mins = ((double) totalSuccess / totalCompleted) * 100.0;
            if (this.successRateInLast15mins < 75.0) {
                this.gatewayStatus = GatewayStatus.INACTIVE;
                this.inactiveSince = currentTime;
            }
        } else {
            this.successRateInLast15mins = 100.0;
        }
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public GatewayStatus getStatus() {
        return gatewayStatus;
    }

    public void setStatus(GatewayStatus gatewayStatus) {
        this.gatewayStatus = gatewayStatus;
    }

    public List<Transaction> getTxnsToProcess() {
        return txnsToProcess;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getInactiveSince() {
        return inactiveSince;
    }

    public void setInactiveSince(int inactiveSince) {
        this.inactiveSince = inactiveSince;
    }

    public Double getSuccessRateInLast15mins() {
        return successRateInLast15mins;
    }

    public void setSuccessRateInLast15mins(Double successRateInLast15mins) {
        this.successRateInLast15mins = successRateInLast15mins;
    }
}
