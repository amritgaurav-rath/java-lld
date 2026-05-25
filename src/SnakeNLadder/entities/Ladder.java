package SnakeNLadder.entities;

public class Ladder extends Connector {
    public Ladder(int start, int end) {
        super(start,end);
        if(start>=end) {
            throw new IllegalArgumentException("For ladder: end should be greater than start");
        }
    }

    @Override
    public String getEncounterMessage() {
        return "Found Ladder at: " + getStart();
    }
}
