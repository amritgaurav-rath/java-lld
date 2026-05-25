package atmMachine;

import atmMachine.entities.Account;
import atmMachine.entities.BankingSvc;
import atmMachine.entities.Card;
import atmMachine.enums.OperationType;

public class ATMMain {
    public static void main(String args[]) {
        // 1. Initialize ATM and Banking System
        ATMController atm = ATMController.getInstance();
        BankingSvc bankService = atm.getBankService();

        // 2. Setup Mock Data: Create Account and Card
        System.out.println("--- System Setup ---");
        Account myAccount = bankService.createAccount("ACC1001", 5000.0);
        Card myCard = bankService.createCard("CARD123", "1234");
        bankService.linkCardToAccount(myCard, myAccount);
        System.out.println("Account and Card created successfully.");

        // Scenario 1: Operation without inserting card
        System.out.println("\n--- Scenario 1: Operation without card ---");
        atm.selectOperation(OperationType.BALANCE_ENQUIRY, 0);

        // Scenario 2: Insert invalid card
        System.out.println("\n--- Scenario 2: Insert invalid card ---");
        atm.insertCard("UNKNOWN_CARD");

        // Scenario 3: Insert valid card, enter wrong PIN
        System.out.println("\n--- Scenario 3: Invalid PIN ---");
        atm.insertCard("CARD123");
        atm.enterPin("0000"); // Wrong PIN, should eject card

        // Scenario 4: Valid authentication and Balance Enquiry
        System.out.println("\n--- Scenario 4: Valid Auth & Balance Enquiry ---");
        atm.insertCard("CARD123");
        atm.enterPin("1234"); // Correct PIN
        atm.selectOperation(OperationType.BALANCE_ENQUIRY, 0);

        // Scenario 5: Cash Deposit
        System.out.println("\n--- Scenario 5: Cash Deposit ---");
        atm.selectOperation(OperationType.DEPOSIT, 1500);
        atm.selectOperation(OperationType.BALANCE_ENQUIRY, 0); // Check updated balance

        // Scenario 6: Successful Cash Withdrawal
        System.out.println("\n--- Scenario 6: Cash Withdrawal ---");
        atm.selectOperation(OperationType.WITHDRAW, 1200);
        atm.selectOperation(OperationType.BALANCE_ENQUIRY, 0);

        System.out.println("\n--- Scenario 7: Cash Withdrawal 2 ---");
        try {
            atm.selectOperation(OperationType.WITHDRAW, 1210);
        } catch (Exception e) {
            System.err.println("Exception caught: " + e.getMessage());
        }
        atm.selectOperation(OperationType.BALANCE_ENQUIRY, 0);

        // Scenario 7: Withdraw more than balance (or ATM capacity)
        System.out.println("\n--- Scenario 8: Invalid Withdrawal Amount ---");
        try {
            atm.selectOperation(OperationType.WITHDRAW, 100000);
        } catch (Exception e) {
            System.err.println("Exception caught: " + e.getMessage());
        }

        System.out.println("\n--- ATM Testing Completed ---");
    }
}
