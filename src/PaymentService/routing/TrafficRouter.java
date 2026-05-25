package PaymentService.routing;

import PaymentService.entities.Gateway;
import java.util.List;

public interface TrafficRouter {
    Gateway route(List<Gateway> activeGateways);
}
