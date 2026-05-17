package strategy.parkingStrategy;

import java.util.List;
import entities.*;

public interface ParkingStrategy {
    Slot findSlot(List<Floor> floors, VehicleType vehicleType);
}
