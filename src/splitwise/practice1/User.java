package splitwise.practice1;

import java.util.*;

public class User {
    private String userName;
    private Map<User, Double> balanceSheet;

    public User(String userName) {
        this.userName = userName;
        this.balanceSheet = new HashMap<>();
    }

    public Map<User,Double> getBalanceSheet() {
        return this.balanceSheet;
    }

    public String getUserName() {
        return userName;
    }
}
