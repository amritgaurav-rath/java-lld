package entities;
import java.util.*;

public class Board {
    private Map<Integer, Connector> connectors;
    private int size;

    public Board(int size) {
        this.size = size;
        this.connectors = new HashMap<>();
    }
    public void setConnectors(List<Connector> connectors) {
        for(Connector conn:connectors) {
            if(conn.getStart() > this.size || conn.getEnd() > this.size) {
                throw new IllegalArgumentException("Invalid connector");
            }
            this.connectors.put(conn.getStart(), conn);
        }
    }
    public Connector getConnector(int pos) {
        if(this.connectors.containsKey(pos)) {
            return this.connectors.get(pos);
        }
        return null;
    }
    public int getSize() {
        return this.size;
    }
}
