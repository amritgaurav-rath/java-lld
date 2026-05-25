package splitwise.practice2.entities;

import java.util.*;
import splitwise.practice2.strategy.*;

public class Expense {
    String expenseId;
    String expenseName;
    Double amount;
    User paidBy;
    List<Split> splits;

    public Expense(ExpenseBuilder expenseBuilder) {
        this.expenseId = expenseBuilder.expenseId;
        this.expenseName = expenseBuilder.expenseName;
        this.amount = expenseBuilder.amount;
        this.paidBy = expenseBuilder.paidBy;
        this.splits = expenseBuilder.splitStrategy.createSplits(expenseBuilder.amount, expenseBuilder.users, expenseBuilder.values);
    }

    public String getExpenseId() {
        return this.expenseId;
    }

    public String getExpenseName() {
        return this.expenseName;
    }

    public Double getAmount() {
        return this.amount;
    }

    public User getPaidUser() {
        return this.paidBy;
    }

    public List<Split> getSplits() {
        return this.splits;
    }

    public static class ExpenseBuilder {
        String expenseId;
        String expenseName;
        User paidBy;
        Double amount;
        List<User> users;
        List<Double> values;
        SplitStrategy splitStrategy;

        public void setExpenseId(String expenseID) {
            this.expenseId = expenseID;
        }

        public void setExpenseName(String expenseName) {
            this.expenseName = expenseName;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }
        
        public void setPaidBy(User paidBy) {
            this.paidBy = paidBy;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }
        public void setValues(List<Double> values) {
            this.values = values;
        }

        public void setStrategy(SplitStrategy splitStrategy) {
            this.splitStrategy = splitStrategy;
        }
        public Expense build() {
            if(this.expenseId == null || 
            this.expenseName == null || 
            this.paidBy == null || 
            this.amount == null ||
            this.splitStrategy == null ||
            this.users == null) {
                throw new IllegalArgumentException("All params are not filled");
            }

            return new Expense(this);
        }
    }
}
