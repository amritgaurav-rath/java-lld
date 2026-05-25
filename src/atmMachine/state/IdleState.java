package atmMachine.state;

import atmMachine.ATMController;
import atmMachine.enums.OperationType;
import atmMachine.entities.Card;

public class IdleState implements ATMState {
    public void insertCard(ATMController atmController, String cardNumber) {
        System.err.println("Card inserted");

        Card card = atmController.getCard(cardNumber);
        if(card == null) {
            ejectCard(atmController);
        } else {
            atmController.setCurrentCard(card);
            atmController.changeState(new HasCardState());
        }
    }
    public void enterPin(ATMController atmController, String pin) {
        System.err.println("Insert Card First");
    }
    public void selectOperation(ATMController atmController, OperationType operationType, int... value) {
        System.err.println("Insert Card First");
    }
    public void ejectCard(ATMController atmController) {
        System.out.println("Err: card not found");
        atmController.reset();
    }
}
