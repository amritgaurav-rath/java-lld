package atmMachine;

import atmMachine.chainOfResp.*;
import atmMachine.entities.*;
import atmMachine.enums.OperationType;
import atmMachine.state.*;

public class ATMController {
    private ATMState currentState;
    private static ATMController INSTANCE;
    private static BankingSvc bankService;
    private CashDispenser cashDispenser;
    private Card currentCard;

    private ATMController() {
        this.currentState = new IdleState();
        bankService = new BankingSvc();

        DispenseChain c1 = new NoteDispenser100(50);
        DispenseChain c2 = new NoteDispenser50(50);
        DispenseChain c3 = new NoteDispenser20(50);

        c1.setNextDispense(c2);
        c2.setNextDispense(c3);

        this.cashDispenser = new CashDispenser(c1);
    }

    public static ATMController getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ATMController();
        }
        return INSTANCE;
    }

    public void setCurrentCard(Card card) { this.currentCard = card; }
    public void changeState(ATMState state) {
        this.currentState = state;
    }

    public void insertCard(String cardNumber) {
        currentState.insertCard(this, cardNumber);
    }

    public void enterPin(String pin) {
        currentState.enterPin(this, pin);
    }

    public void selectOperation(OperationType op, int value) { currentState.selectOperation(this, op, value); }

    public Card getCard(String cardNumber) {
        return bankService.getCard(cardNumber);
    }

    public boolean authenticate(String pin) {
        return bankService.authenticate(currentCard, pin);
    }

    public void checkBalance() {
        double balance = bankService.getBalance(currentCard);
        System.out.printf("Your current account balance is: Rs%.2f%n", balance);
    }

    public void withdrawCash(int amount) {
        if (!cashDispenser.canDispenseCash(amount)) {
            throw new IllegalStateException("Insufficient cash available in the ATM.");
        }

        bankService.withdrawMoney(currentCard, amount);

        try {
            cashDispenser.dispenseCash(amount);
        } catch (Exception e) {
            bankService.depositMoney(currentCard, amount); // Deposit back if dispensing fails
        }
    }

    public void depositCash(int amount) {
        bankService.depositMoney(currentCard, amount);
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public BankingSvc getBankService() {
        return bankService;
    }

    public void reset() {
        this.currentCard = null;
        this.currentState = new IdleState();
    }
}
