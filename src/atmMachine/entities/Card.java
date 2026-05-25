package atmMachine.entities;

public class Card {
    String cardNumber;
    String pin;

    public Card(String cardNumber) {
        this.cardNumber = cardNumber;
        this.pin = null;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }
    public String getPin() {
        return this.pin;
    }
}
