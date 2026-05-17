package splitwise.practice1;

public class Split {
    User user;
    Double amount;

    public Split(User user, Double amount) {
        this.user = user;
        this.amount = amount;
    }

    public User getUser() {
        return this.user;
    }

    public Double getAmount() {
        return this.amount;
    }
}
