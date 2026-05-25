package SnakeNLadder.entities;

public abstract class Connector {
    private int start;
    private int end;

    public Connector(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public abstract String getEncounterMessage();
}
