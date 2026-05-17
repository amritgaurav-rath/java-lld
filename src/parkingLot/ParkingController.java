import entities.*;
import strategy.feeStrategy.FeeStrategy;
import strategy.feeStrategy.NormalFeeStrategy;
import strategy.parkingStrategy.NearestParkingStrategy;
import strategy.parkingStrategy.ParkingStrategy;
import java.util.ArrayList;
import java.util.List;

public class ParkingController {
    List<Floor> floors;
    List<Ticket> tickets;
    ParkingStrategy parkingStrategy;
    FeeStrategy feeStrategy;
    public static ParkingController instance;

    public ParkingController() {
        this.floors = new ArrayList<>();
        this.tickets = new ArrayList<>();
        this.parkingStrategy = new NearestParkingStrategy();
        this.feeStrategy = new NormalFeeStrategy();
    }

    public static ParkingController getInstance() {
        if (instance == null) {
            instance = new ParkingController();
        }
        return instance;
    }

    public void setFloors(List<Floor> f) {
        this.floors.addAll(f);
    }

    public void setParkingStrategy(ParkingStrategy strategy) {
        this.parkingStrategy = strategy;
    }

    public void setFeeStrategy(FeeStrategy strategy) {
        this.feeStrategy = strategy;
    }

    

    public String issueTicket(Vehicle vehicle, Integer occupiedStartTs) {
        Slot slot = parkingStrategy.findSlot(this.floors, vehicle.getType());
        if (slot == null) {
            System.out.println("Parking Lot is full for vehicle type: " + vehicle.getType());
            return null;
        }

        Integer floorNo = null;
        for (Floor f : this.floors) {
            if (f.getSlots().contains(slot)) {
                floorNo = f.getFloorNumber();
                break;
            }
        }

        slot.occupySlot(vehicle);
        
        String ticketId = "TKT-" + (this.tickets.size() + 1);
        Ticket ticket = new Ticket();
        ticket.addTicket(ticketId, slot.getSlotId(), floorNo, vehicle, occupiedStartTs);
        this.tickets.add(ticket);
        
        return ticketId;
    }

    public void checkOutTicket(String ticketId, Integer checkoutTime) {
        Ticket ticket = null;
        for (Ticket t : this.tickets) {
            if (t.getTicketId().equals(ticketId) && t.getTicketStatus() == TicketStatus.PARKED) {
                ticket = t;
                break;
            }
        }
        
        if (ticket == null) {
            System.out.println("Invalid or already checked out ticket ID");
            return;
        }
        
        double price = feeStrategy.getAmount(ticket, checkoutTime);
        ticket.setTotalAmount(price);
        ticket.checkOut();
        
        // release slot
        for (Floor f : this.floors) {
            for (Slot s : f.getSlots()) {
                if (s.getSlotId().equals(ticket.getSlotId())) {
                    s.releaseSlot();
                    break;
                }
            }
        }
        
        System.out.println("Vehicle checked out. Price: " + price);
    }

    public void displayAvailableSpots() {
        System.out.println("--- Available Spots ---");
        for (Floor f : this.floors) {
            int availableBikes = 0;
            int availableCars = 0;
            int availableTrucks = 0;
            
            for (Slot s : f.getSlots()) {
                if (s.getSlotState() == SlotState.AVAILABLE) {
                    switch (s.getVehicleType()) {
                        case BIKE: availableBikes++; break;
                        case CAR: availableCars++; break;
                        case TRUCK: availableTrucks++; break;
                    }
                }
            }
            System.out.println("Floor " + f.getFloorNumber() + " -> Bikes: " + availableBikes + ", Cars: " + availableCars + ", Trucks: " + availableTrucks);
        }
        System.out.println("-----------------------");
    }
}
