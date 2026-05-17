package strategy.feeStrategy;
import entities.*;

public class NormalFeeStrategy implements FeeStrategy {
    public double getAmount(Ticket ticket, int currTime) {
        double rate;
        switch (ticket.getVehicle().getType()) {
            case BIKE:
             rate = 0.05;
             break;
            case CAR:
             rate = 0.10;
             break;
            case TRUCK: 
             rate = 0.20;
             break;
            default:
             throw new IllegalArgumentException("Unsupported vehicle type");
        }
        return rate * (currTime - ticket.getStartTime());
    }
}
