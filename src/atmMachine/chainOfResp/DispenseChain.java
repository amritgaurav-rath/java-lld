package atmMachine.chainOfResp;

public interface DispenseChain {
    void setNextDispense(DispenseChain dispenseChain);
    void dispense(int amount);
    boolean canDispense(int amount);
}
