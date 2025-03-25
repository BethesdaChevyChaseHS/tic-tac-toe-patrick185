package bcc.tictactoe;

public class SlightlySmartAI extends Player {
    @Override
    public Move makeMove(Board board, Mark mark) {//note - board coming in is a copy, so we can modify it
        int row, col;
        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                if (board.getGrid()[i][j].equals(Mark.EMPTY)) {
                    board.makeMove(i, j, mark);
                    if(board.checkWin()!=null) {
                        if (board.checkWin().equals(mark)) {

                            return new Move(i, j);
                            
                        }
                        else {
                            board.clearCell(i, j);
                        }
                    }
                }
            }
        }

        do {
            row = (int) (Math.random() * 3);
            col = (int) (Math.random() * 3);
        } while (!board.getGrid()[row][col].equals(Mark.EMPTY));
        return new Move(row, col);
        
    }

    public String toString() {
        return "Slightly Smart AI";
    }
    
}
