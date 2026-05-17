package entities;

public class Dice {
    private int maxFaces;

    public Dice(int maxFaces) {
        this.maxFaces = maxFaces;
    }

    public int roll() {
        return (int)(Math.random() * maxFaces) + 1;
    }
}
