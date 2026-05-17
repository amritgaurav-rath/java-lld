package splitwise.practice1;
import java.util.*;

public class Expense {
    private String expenseId;
    private Double expenseAmount;
    private User paidBy;
    private List<Split> splits;

    public Expense(String expenseId, Double amount, User paidBy, List<User> paidTo, List<Double> distribution,SplitStrategy splitStrategy) {
        this.expenseId = expenseId;
        this.expenseAmount = amount;
        this.paidBy = paidBy;
        this.splits = splitStrategy.SplitExpense(amount, paidBy, paidTo, distribution);
    }

    public List<Split> getSplits() {
        return splits;
    }

    public User getPaidBy() {
        return paidBy;
    }
}
