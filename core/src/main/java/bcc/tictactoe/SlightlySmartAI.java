package bcc.tictactoe;

public class SlightlySmartAI extends Player {
    @Override
    public Move makeMove(Board board, Mark mark) {//note - board coming in is a copy, so we can modify it
        
        return new Move(0, 0);
    }

    public String toString() {
        return "Slightly Smart AI";
    }
    
}
