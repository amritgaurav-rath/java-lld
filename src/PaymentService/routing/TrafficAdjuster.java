package PaymentService.routing;

import PaymentService.entities.Gateway;
import java.util.List;

public interface TrafficAdjuster {
    void adjustForNewNode(List<Gateway> allActiveGateways, Gateway newGateway);
    void adjustForRemovedNode(List<Gateway> remainingActiveGateways);
}
