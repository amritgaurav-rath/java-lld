package atmMachine.state;

import atmMachine.ATMController;
import atmMachine.enums.OperationType;

public class AuthenticatedState implements ATMState {
    public void insertCard(ATMController atmController, String cardNumber) {
        System.err.println("card already inserted.");
    }

    public void enterPin(ATMController atmController, String pin) {
        System.err.println("pin already entered.");
    }

    public void selectOperation(ATMController atmController, OperationType operationType, int... value) {
        switch (operationType) {
            case OperationType.DEPOSIT:
                if (value.length == 0 || value[0] <= 0) {
                    System.err.println("value not provided");
                    ejectCard(atmController);
                }
                System.out.println("Processing deposit for Rs" + value[0]);
                atmController.depositCash(value[0]);
                break;
            case OperationType.BALANCE_ENQUIRY:
                atmController.checkBalance();
                break;
            case OperationType.WITHDRAW:
                if (value.length == 0 || value[0] <= 0) {
                    System.err.println("value not provided");
                    ejectCard(atmController);
                }
                System.out.println("Processing withdrawal for Rs." + value[0]);
                atmController.withdrawCash(value[0]);
                break;
            default:
                System.err.println("");
        }
    }

    public void ejectCard(ATMController atmController) {
        System.err.println("Ejecting card. Thank you!");
        atmController.setCurrentCard(null);
        atmController.changeState(new IdleState());
    }
}
