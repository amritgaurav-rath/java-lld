import entities.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Parking Lot Test ---");
        
        // 1. Initialize Controller
        ParkingController controller = ParkingController.getInstance();
        
        // 2. Set up Floors and Slots
        Floor floor1 = new Floor(1);
        floor1.addSlots(new Slot("S1-F1", VehicleType.BIKE));
        floor1.addSlots(new Slot("S2-F1", VehicleType.BIKE));
        floor1.addSlots(new Slot("S3-F1", VehicleType.CAR));
        
        Floor floor2 = new Floor(2);
        floor2.addSlots(new Slot("S1-F2", VehicleType.CAR));
        floor2.addSlots(new Slot("S2-F2", VehicleType.TRUCK));
        
        controller.setFloors(Arrays.asList(floor1, floor2));
        System.out.println("Parking lot initialized with 2 floors.");
        
        // 3. Create Vehicles
        Vehicle bike1 = new Vehicle("B-001", VehicleType.BIKE);
        Vehicle bike2 = new Vehicle("B-002", VehicleType.BIKE);
        Vehicle bike3 = new Vehicle("B-003", VehicleType.BIKE); // To test full capacity for bikes
        
        Vehicle car1 = new Vehicle("C-001", VehicleType.CAR);
        Vehicle car2 = new Vehicle("C-002", VehicleType.CAR);
        Vehicle truck1 = new Vehicle("T-001", VehicleType.TRUCK);
        
        // 4. Issue Tickets
        System.out.println("\n--- Issuing Tickets ---");
        String ticketB1 = controller.issueTicket(bike1, 100);
        System.out.println("Bike 1 parked with ticket: " + ticketB1);
        
        String ticketC1 = controller.issueTicket(car1, 110);
        System.out.println("Car 1 parked with ticket: " + ticketC1);
        
        String ticketT1 = controller.issueTicket(truck1, 120);
        System.out.println("Truck 1 parked with ticket: " + ticketT1);
        
        // Test nearest slot logic for car2
        String ticketC2 = controller.issueTicket(car2, 130);
        System.out.println("Car 2 parked with ticket: " + ticketC2);
        
        // 5. Test Full Capacity
        System.out.println("\n--- Testing Full Capacity ---");
        String ticketB2 = controller.issueTicket(bike2, 140);
        System.out.println("Bike 2 parked with ticket: " + ticketB2);
        
        // There are only 2 bike slots (both on floor 1). They should be occupied now.
        // Trying to park a 3rd bike should fail.
        String ticketB3 = controller.issueTicket(bike3, 150);
        if (ticketB3 == null) {
            System.out.println("Correctly handled full capacity for bikes.");
        }
        
        // 6. Check Out Tickets
        System.out.println("\n--- Checking out Vehicles ---");
        // Bike 1: in at 100, out at 200. Parked time: 100. Rate: 0.05. Amount: 100 * 0.05 = 5.0
        if (ticketB1 != null) {
            System.out.print("Checking out Bike 1: ");
            controller.checkOutTicket(ticketB1, 200);
        }
        
        // Car 1: in at 110, out at 210. Parked time: 100. Rate: 0.10. Amount: 100 * 0.10 = 10.0
        if (ticketC1 != null) {
            System.out.print("Checking out Car 1: ");
            controller.checkOutTicket(ticketC1, 210);
        }
        
        // Truck 1: in at 120, out at 220. Parked time: 100. Rate: 0.20. Amount: 100 * 0.20 = 20.0
        if (ticketT1 != null) {
            System.out.print("Checking out Truck 1: ");
            controller.checkOutTicket(ticketT1, 220);
        }
        
        // 7. Check if freed slot can be reused
        System.out.println("\n--- Testing Slot Reusability ---");
        String ticketB3_retry = controller.issueTicket(bike3, 230);
        System.out.println("Bike 3 parked (reused slot) with ticket: " + ticketB3_retry);
    }
}
