package atmMachine.entities;
import java.util.*;

public class BankingSvc {
    private Map<String,Card> cardMap;
    private Map<Card,Account> cardAccountRelationMap;
    private Map<String,Account> accounts;

    public BankingSvc() {
        this.cardMap = new HashMap<>();
        this.cardAccountRelationMap = new HashMap<>();
        this.accounts = new HashMap<>();
    }
    // - createAccount;
    // - createCard;
    // - linkCardToAccount;
    // - depositCash(cardId, int amount);
    // - withDrawCash(cardId, int amount);
    // - balanceEnquiry(cardId);
    // - authenticate(cardId,pin);

    public Account createAccount(String accountNumber, double initialBalance) {
        Account account = new Account(accountNumber, initialBalance);
        accounts.put(accountNumber, account);
        return account;
    }

    public Card createCard(String cardNumber, String pin) {
        Card card = new Card(cardNumber);
        card.setPin(pin);
        cardMap.put(cardNumber, card);
        return card;
    }

    public boolean authenticate(Card card, String pin) {
        return card.getPin().equals(pin);
    }

    public Card getCard(String cardNumber) {
        return cardMap.getOrDefault(cardNumber, null);
    }

    public double getBalance(Card card) {
        return cardAccountRelationMap.get(card).getBalance();
    }

    public void withdrawMoney(Card card, double amount) {
        cardAccountRelationMap.get(card).withdrawCash(amount);
    }

    public void depositMoney(Card card, double amount) {
        cardAccountRelationMap.get(card).depositCash(amount);
    }

    public void linkCardToAccount(Card card, Account account) {
        account.getCards().put(card.getCardNumber(), card);
        cardAccountRelationMap.put(card, account);
    }
}
