package bcc.tictactoe;

public class Human extends Player {
    @Override
    public Move makeMove(Board board, Mark mark) {
        return new Move(-1, -1); // need to wait for users input
    }
    
    public String toString() {
        return "Human";
    }
}
