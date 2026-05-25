package splitwise.practice2.entities;

import java.util.Objects;

public class User {
    String userId;
    String userName;
    BalanceSheet balances;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.balances = new BalanceSheet(this);
    }

    public BalanceSheet getBalances() {
        return this.balances;
    }

    public String getName() {
        return this.userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
