package PaymentService.entities;

import java.util.UUID;

public class Transaction {
    private String transactionId;
    private String orderId;
    private String userId;
    private TransactionStatus status;
    private int timestamp;

    public Transaction(String orderId, String userId) {
        this.transactionId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.userId = userId;
        this.status = TransactionStatus.INPROGRESS;
        this.timestamp = (int) (System.currentTimeMillis() / 1000);
    }

    public String getTransactionId() { return transactionId; }
    public String getOrderId() { return orderId; }
    public String getUserId() { return userId; }
    public TransactionStatus getStatus() { return status; }
    public void setStatus(TransactionStatus status) { this.status = status; }
    public int getTimestamp() { return timestamp; }
    public void setTimestamp(int timestamp) { this.timestamp = timestamp; }
}
