
package bcc.tictactoe;
public class Board {
    private Mark[][] grid;
    public Board() {
        //initialize grid to be 3x3
        grid = new Mark[3][3];
        reset();
    }

    public void reset() {
        //should restart the game - set all cells to empty
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = Mark.EMPTY; 
            }
        }
        
    }

    public boolean makeMove(Move move, Mark mark) {//make move on the grid
        
        return false;
    }

    public boolean makeMove(int row, int col, Mark mark) {
        //make a move on the grid
        if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == null) {
            grid[row][col] = mark;
            return true; 
        }
        return false;
    }

    public void clearCell(int row, int col) {
       //set the given grid cell to empty
        if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length) {
        
            grid[row][col] = null;
        }
       
    }
    public boolean isFull() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == Mark.EMPTY) { 
                    return false;
                }
            }
        }
        return true;
    }

    public Mark[][] getGrid() {
        return grid;
    }

    /**
     * return 'Mark.X' if X wins, 'Mark.O' if O wins, 'Mark.Tie' if tie, or 'null' if still in progress
     */
    public Mark checkWin() {//return null if game not over
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] != null && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
                return grid[i][0];
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (grid[0][j] != null && grid[0][j] == grid[1][j] && grid[1][j] == grid[2][j]) {
                return grid[0][j];
            }
        }

        // Check diagonals
        if (grid[0][0] != null && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
            return grid[0][0];
        }
        if (grid[0][2] != null && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
            return grid[0][2];
        }

        // Check for a tie
        if (isFull()) {
            return Mark.TIE;
        }

        // Game is still in progress
        return null;
    }

    public Board clone() {
        //return a copy of the grid
        Board clonedBoard = new Board();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                clonedBoard.grid[i][j] = this.grid[i][j];
            }
        }
        return clonedBoard;
       
    }
}
