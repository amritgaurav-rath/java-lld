package entities;
import java.util.ArrayList;
import java.util.List;

public class Floor {
    private Integer floorNo;
    private List<Slot> slots;

    public Floor(Integer floorNo) {
        this.floorNo = floorNo;
        this.slots = new ArrayList<>();
    }

    public void addSlots(Slot slot) {
        this.slots.add(slot);
    }

    public Integer getFloorNumber() {
        return this.floorNo;
    }

    public List<Slot> getSlots() {
        return this.slots;
    }
}
