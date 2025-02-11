
package bcc.tictactoe;
public class Board {
    private Mark[][] grid;
    public Board() {
        //initialize grid to be 3x3 
    }

    public void reset() {
        //should restart the game - set all cells to empty

    }

    public boolean makeMove(Move move, Mark mark) {//make move on the grid
        return false;
    }

    public boolean makeMove(int row, int col, Mark mark) {
        //make a move on the grid
        return false;
    }

    public void clearCell(int row, int col) {
       //set the given grid cell to empty
    }
    public boolean isFull() {
       //check if grid is full(and thus game is a tie)
       return false;
    }

    public Mark[][] getGrid() {
        return grid;
    }

    /**
     * return 'Mark.X' if X wins, 'Mark.O' if O wins, 'Mark.Tie' if tie, or 'null' if still in progress
     */
    public Mark checkWin() {//return null if game not over
        // Check rows
       
        // Check columns
       
        // Check diagonals

        // Check tie

        return null; // Game not over
    }

    public Board clone() {
        //return a copy of the grid
       return null;
    }
}
