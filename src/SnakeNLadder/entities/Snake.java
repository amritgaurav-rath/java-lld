package entities;

public class Snake extends Connector {
    public Snake(int start,int end) {
        super(start,end);
        if(start<=end) {
            throw new IllegalArgumentException("For Snake: end should be smaller than start");
        }
    }
}
