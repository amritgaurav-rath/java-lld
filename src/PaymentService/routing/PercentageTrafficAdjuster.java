package PaymentService.routing;

import PaymentService.entities.Gateway;
import java.util.List;

public class PercentageTrafficAdjuster implements TrafficAdjuster {
    @Override
    public void adjustForNewNode(List<Gateway> allActiveGateways, Gateway newGateway) {
        if (allActiveGateways == null || allActiveGateways.isEmpty()) {
            return;
        }

        double existingWeightSum = 0.0;
        for (Gateway g : allActiveGateways) {
            if (g != newGateway) {
                existingWeightSum += g.getWeight();
            }
        }

        // The new gateway keeps exactly its requested weight.
        // We scale all other existing gateways to fit inside the remaining space.
        double targetSum = 100.0 - newGateway.getWeight();
        if (targetSum < 0)
            targetSum = 0.0;

        if (existingWeightSum > 0) {
            for (Gateway g : allActiveGateways) {
                if (g != newGateway) {
                    double newWeight = g.getWeight() * targetSum / existingWeightSum;
                    g.setWeight(newWeight);
                }
            }
        }
    }

    @Override
    public void adjustForRemovedNode(List<Gateway> remainingActiveGateways) {
        if (remainingActiveGateways == null || remainingActiveGateways.isEmpty()) {
            return;
        }

        double totalWeightSum = 0.0;
        for (Gateway g : remainingActiveGateways) {
            totalWeightSum += g.getWeight();
        }

        if (totalWeightSum > 0 && totalWeightSum != 100.0) {
            for (Gateway g : remainingActiveGateways) {
                double newWeight = g.getWeight() * 100.0 / totalWeightSum;
                g.setWeight(newWeight);
            }
        }
    }
}
