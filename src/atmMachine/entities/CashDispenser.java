package atmMachine.entities;

import atmMachine.chainOfResp.DispenseChain;

public class CashDispenser {
    public DispenseChain chain;

    public CashDispenser(DispenseChain chain) {
        this.chain = chain;
    }

    public boolean canDispenseCash(int cashAmount) {
        if (cashAmount % 10 != 0) {
            return false;
        }
        return this.chain.canDispense(cashAmount);
    }

    public void dispenseCash(int cashAmount) {
        this.chain.dispense(cashAmount);
    }
}
