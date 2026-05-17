package entities;

public class Slot {
    final String slotId;
    Vehicle vehicle;
    VehicleType allocatedVehicleType;
    SlotState slotState;

    public Slot(String slotId, VehicleType vehicleType) {
        this.slotId = slotId;
        this.allocatedVehicleType = vehicleType;
        this.slotState = SlotState.AVAILABLE;
    }

    public void occupySlot(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.slotState = SlotState.OCCUPIED;
    }

    public void releaseSlot() {
        this.vehicle = null;
        this.slotState = SlotState.AVAILABLE;
    }

    public SlotState getSlotState() {
        return this.slotState;
    }

    public VehicleType getVehicleType() {
        return this.allocatedVehicleType;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    } 

    public String getSlotId() {
        return this.slotId;
    }
}
