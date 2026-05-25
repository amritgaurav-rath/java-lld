package atmMachine.state;

import atmMachine.ATMController;
import atmMachine.enums.OperationType;

public interface ATMState {
    void insertCard(ATMController atmController, String cardNumber);
    void enterPin(ATMController atmController, String pin);
    void selectOperation(ATMController atmController, OperationType operationType, int... value);
    void ejectCard(ATMController atmController);
}
