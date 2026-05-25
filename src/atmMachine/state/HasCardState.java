package atmMachine.state;

import atmMachine.ATMController;
import atmMachine.enums.OperationType;

public class HasCardState implements ATMState {
    public void insertCard(ATMController atmController, String cardNumber) {
        System.err.println("Card already inserted");
    }
    public void enterPin(ATMController atmController, String pin) {
        System.err.println("Entering pin");

        boolean isAuthDone = atmController.authenticate(pin);
        if(isAuthDone) {
            System.err.println("Auth successfull");
            atmController.changeState(new AuthenticatedState());
        } else {
            System.err.println("Auth failed");
            ejectCard(atmController);
        }
    }
    public void selectOperation(ATMController atmController, OperationType operationType, int... value) {
        System.err.println("Card already inserted");
    }
    public void ejectCard(ATMController atmController) {
        atmController.setCurrentCard(null);
        atmController.changeState(new IdleState());
        System.err.println("Card is ejected. Thank you!");
    }
}
