package splitwise.practice2.entities;

public class Transaction {
    User from;
    User to;
    Double amount;

    public Transaction(User u1, User u2, Double amountPaid) {
        this.from = u1;
        this.to = u2;
        this.amount = amountPaid;
    }
}
