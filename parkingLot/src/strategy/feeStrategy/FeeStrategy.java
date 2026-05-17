package strategy.feeStrategy;

import entities.*;

public interface FeeStrategy {
    double getAmount(Ticket ticket, int currTime);
}