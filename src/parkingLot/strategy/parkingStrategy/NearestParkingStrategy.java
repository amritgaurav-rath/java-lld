package strategy.parkingStrategy;

import entities.*;
import java.util.*;

public class NearestParkingStrategy implements ParkingStrategy {
    @Override
    public Slot findSlot(List<Floor> floors, VehicleType vehicleType) {
        floors.sort(Comparator.comparingInt(Floor::getFloorNumber));
        for (Floor floor : floors) {
            for (Slot slot : floor.getSlots()) {
                if (slot.getSlotState() == SlotState.AVAILABLE &&
                    slot.getVehicleType() == vehicleType) {
                    return slot; // first matching slot = nearest
                }
            }
        }
        return null; // no suitable slot found
    }
}
