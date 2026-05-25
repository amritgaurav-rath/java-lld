package PaymentService.routing;

import PaymentService.entities.Gateway;
import java.util.List;
import java.util.ArrayList;

public class WeightedTrafficRouter implements TrafficRouter {
    @Override
    public Gateway route(List<Gateway> activeGateways) {
        if (activeGateways == null || activeGateways.isEmpty()) {
            throw new IllegalStateException("No active gateways available");
        }

        int totalTraffic = 0;
        for (Gateway g : activeGateways) {
            totalTraffic += g.getTxnsToProcess().size();
        }

        List<Gateway> sortedGateways = new ArrayList<>(activeGateways);
        // Sort by weight in descending order
        sortedGateways.sort((g1, g2) -> Double.compare(g2.getWeight(), g1.getWeight()));

        if (totalTraffic == 0) {
            return sortedGateways.get(0);
        }

        for (Gateway g : sortedGateways) {
            double currentTrafficPercentage = (g.getTxnsToProcess().size() * 100.0) / totalTraffic;
            if (currentTrafficPercentage < g.getWeight()) {
                return g;
            }
        }

        // Fallback if all exceed their exact weights
        return sortedGateways.get(0);
    }
}
