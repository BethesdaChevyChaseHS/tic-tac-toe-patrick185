package bcc.tictactoe;

public class RandomAI extends Player {
    @Override
    public Move makeMove(Board board, Mark mark) {
        int row, col;
        do {
            row = (int) (Math.random() * 3);
            col = (int) (Math.random() * 3);
        } while (!board.getGrid()[row][col].equals(Mark.EMPTY));
        return new Move(row, col);
    }
    
    public String toString() {
        return "RandomAI";
    }
}