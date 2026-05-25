package SnakeNLadder.entities;

public class Player {
    private String playerName;
    private Integer position;

    public Player(String name) {
        this.playerName = name;
        position = 0;
    }

    public void setPosition(int pos) {
        this.position = pos;
    }
    public int getPosition() {
        return this.position;
    }
    public String getPlayerName() {
        return this.playerName;
    }
}
