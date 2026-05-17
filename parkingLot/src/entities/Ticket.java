package entities;

public class Ticket {
    String ticketId;
    String slotId;
    Integer floorNo;
    Vehicle vehicle; 
    TicketStatus ticketStatus;
    Integer occupiedStartTs;
    Double totalAmount;

    public void addTicket(String ticketId, String slotId, Integer floorNo, Vehicle vehicle,
    Integer inTime) {
        this.ticketId = ticketId;
        this.slotId = slotId;
        this.floorNo = floorNo;
        this.vehicle = vehicle;
        this.ticketStatus = TicketStatus.PARKED;
        this.occupiedStartTs = inTime;
        this.totalAmount = 0.0;
    }

    // Fee calculation logic is handled by FeeStrategy
    public void checkOut() {
        this.ticketStatus = TicketStatus.CHECKEDOUT;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public Integer getStartTime() {
        return this.occupiedStartTs;
    }

    public String getTicketId() {
        return this.ticketId;
    }

    public TicketStatus getTicketStatus() {
        return this.ticketStatus;
    }

    public String getSlotId() {
        return this.slotId;
    }

    public void setTotalAmount(Double amount) {
        this.totalAmount = amount;
    }
}
