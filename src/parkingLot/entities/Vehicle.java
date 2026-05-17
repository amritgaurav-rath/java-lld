package entities;

public class Vehicle {
    String vehicleNumber;
    VehicleType vehicleType;

    public Vehicle(String vehicleNumber, VehicleType vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public VehicleType getType() {
        return this.vehicleType;
    }
    public String getVehicleNumber() {
        return this.vehicleNumber;
    }
}
