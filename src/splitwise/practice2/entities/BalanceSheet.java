package splitwise.practice2.entities;

import java.util.*;

public class BalanceSheet {
    User owner;
    Map<User,Double> balances;

    public BalanceSheet(User user) {
        this.owner = user;
        this.balances = new HashMap<>();
    }

    public void adjustBalance(User user, Double newBalance) {
        if(!this.balances.containsKey(user)) {
            this.balances.put(user, newBalance);
        } else {
            Double balance = balances.get(user);
            this.balances.put(user, balance+newBalance);
        }
    }

    public Double getBalanceForUser(User user) {
        if(!this.balances.containsKey(user)) {
            return 0.0;
        }

        return this.balances.get(user);
    }
}
