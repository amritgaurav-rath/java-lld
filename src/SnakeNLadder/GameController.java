import entities.*;
import java.util.*;

public class GameController {
    Queue<Player> players;
    Board board;
    Dice dice;
    Player winner;
    GameStatus status;

    public GameController(Builder builder) {
        this.players = new LinkedList<>(builder.players);
        this.board = builder.board;
        this.dice = builder.dice;
        this.status = GameStatus.IDLE;
    }

    public void startGame() {
        if(players.size() < 2) {
            throw new IllegalArgumentException("Less Players");
        }
        this.status = GameStatus.STARTED;
        System.err.println("Game Started");

        while(status != GameStatus.COMPLETED) {
            Player currPlayer = players.poll();
            takeTurn(currPlayer);

            if(status != GameStatus.COMPLETED) {
                players.add(currPlayer);
            }
        }

        System.err.println("Game Finished, winner is " + winner.getPlayerName());
    }

    public void takeTurn(Player p) {
        int move = dice.roll();
        System.err.println("Move by "+p.getPlayerName()+" is: "+move);

        int currPosition = p.getPosition();
        int nextPosition = currPosition + move;

        if(nextPosition > board.getSize()) {
            return;
        }
        if(nextPosition == board.getSize()) {
            System.err.println(p.getPlayerName() + " is Winner!");
            this.winner = p;
            this.status = GameStatus.COMPLETED;
            p.setPosition(nextPosition);
            return;
        }

        if(board.getConnector(nextPosition)!=null) {
            int nextPos= board.getConnector(nextPosition).getEnd();
            if(nextPos > nextPosition) {
                System.err.println("Found Ladder at: "+ nextPosition);
            } else {
                System.err.println("Found Snake at: "+ nextPosition);
            }
            nextPosition = nextPos;
            
        }
        p.setPosition(nextPosition);
        System.err.println("Next Pos of "+p.getPlayerName()+" is: "+nextPosition);
        if(move == 6) {
            System.err.println("Its 6, take one more turn");
            takeTurn(p);
        }
    }

    public static class Builder {
        private Board board;
        private Dice dice;
        private Queue<Player> players;

        public Builder setBoard(int size, List<Connector> connectors) {
            this.board = new Board(size);
            this.board.setConnectors(connectors);

            return this;
        }
        public Builder setDice() {
            this.dice = new Dice();

            return this;
        }
        public Builder setPlayers(List<String> playerNames) {
            this.players = new LinkedList<>();
            for(String name:playerNames) {
                Player p = new Player(name);
                this.players.add(p);
            }
            return this;
        }

        public GameController build() {
            if(this.board == null || this.dice == null || this.players == null) {
                throw new IllegalArgumentException("Incomplete game");
            }

            return new GameController(this);
        }
    }
}
