package atmMachine.entities;
import java.util.*;

public class Account {
    String accountId;
    Double balance;
    Map<String,Card> cardMap;

    public Account(String accountId, Double initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
        this.cardMap = new HashMap<>();
    }

    public Map<String,Card> getCards() {
        return this.cardMap;
    }
    public Double getAccountBalance() {
        return this.balance;
    }

    public void depositCash(Double amount) {
        this.balance = this.balance + amount;
    }

    public void withdrawCash(Double amount) {
        this.balance = this.balance - amount;
    }

    public String getId() {
        return this.accountId;
    }

    public Double getBalance() {
        return this.balance;
    }
}
