package atmMachine.chainOfResp;

public class NoteDispenser implements DispenseChain {
    private DispenseChain nextChain;
    private int noteValue;
    private int numNotes;
    private int notesToDispense = 0;

    public NoteDispenser(int noteValue, int numNotes) {
        this.noteValue = noteValue;
        this.numNotes = numNotes;
    }

    public void setNextDispense(DispenseChain nextChain) {
        this.nextChain = nextChain;
    }

    public boolean canDispense(int amount) {
        if(amount<0) {
            System.err.println("Amount cannot be negative");
            return false;
        }
        if(amount==0) {
            return true;
        }

        int maxNotes = Math.min(amount / this.noteValue, this.numNotes);
        for (int i = maxNotes; i >= 0; i--) {
            int remainingAmount = amount - (i * this.noteValue);
            
            if(remainingAmount == 0) {
                this.notesToDispense = i;
                return true;
            }

            if(this.nextChain != null && this.nextChain.canDispense(remainingAmount)) {
                this.notesToDispense = i;
                return true;
            }
        }
        
        this.notesToDispense = 0;
        return false;
    }

    public void dispense(int amount) {
        if(this.notesToDispense > 0) {
            System.out.println("Dispensing " + this.noteValue + " notes, no. of notes: " + this.notesToDispense);
            this.numNotes = this.numNotes - this.notesToDispense;
        }
        
        int remainingAmount = amount - (this.notesToDispense * this.noteValue);
        if(remainingAmount > 0 && this.nextChain != null) {
            this.nextChain.dispense(remainingAmount);
        }
    }
}
