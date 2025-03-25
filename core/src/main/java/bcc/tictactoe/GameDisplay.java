package bcc.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class GameDisplay extends ScreenAdapter {
    private final TicTacToe game;
    private Stage stage;
    private Skin skin;
    
    //up to you to modify these if you'd like!
    private final float BOARD_X = 90;
    private final float BOARD_Y = 70;
    private final float BOARD_WIDTH = 300;
    private final float BOARD_HEIGHT = 300;
    Table boardTable;


    private boolean gameOver = false;
    private Label resultLabel;
    private TextButton playAgainButton;
    private Board board = new Board();
    


    public GameDisplay(TicTacToe game) {
        //set up the screen you you like
        this.game = game;
        System.out.println("created");
        stage = new Stage();
        
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("skins/glassy/glassy-ui.json"));
        Texture backgroundTexture = new Texture(Gdx.files.internal("space_tictactoe.png"));
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);
        System.out.println(game.getCurPlayerObj());
        game.setBoardState(board);
        
        initTableDisplay();
        updateBoardDisplay();
    }

    public void initTableDisplay() {// initializes tic tac toe board - no changes needed 
        boardTable = new Table();
        boardTable.setPosition(BOARD_X, BOARD_Y);
        boardTable.setSize(BOARD_WIDTH, BOARD_HEIGHT);

        // Set the background image.
        Texture backgroundTexture = new Texture(Gdx.files.internal("tictactoe_board.png"));
        TextureRegionDrawable backgroundDrawable = new TextureRegionDrawable(new TextureRegion(backgroundTexture));
        boardTable.setBackground(backgroundDrawable);

       
        // Force the table to layout from the top (so that row 0 appears at the top).
        boardTable.top();

        boardTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                // The click coordinates (clickX, clickY) are relative to the table's
                // bottom-left.
                float cellWidth = BOARD_WIDTH / 3;
                float cellHeight = BOARD_HEIGHT / 3;

                int col = (int) (clickX / cellWidth);
                int rowFromBottom = (int) (clickY / cellHeight);
                // Convert the y-coordinate to a row index where row 0 is at the top.
                int row = 2 - rowFromBottom;

                // Call your board click handler.
                handleBoardClick(row, col);
            }
        });
        stage.addActor(boardTable);
    }

    
    public void handleBoardClick(int row, int col) {
        //checkpoint 2
        //this position was clicked, play the move, then call handle move made
        Mark[][] grid = game.getBoardState().getGrid();
        if(grid[row][col] == Mark.EMPTY && gameOver == false && game.getCurPlayerObj().toString().equals("Human")) {
            grid[row][col] = game.getCurPlayerMark();
            game.nextPlayer();
            handleMoveMade();
        }
        
        
    }

    public void handleMoveMade(){//checkpoint 2
        //call updateBoardDisplay
        //check for a win or tie. If there is one, call showResult() with a message containing the winner, and update the player stats. 
        updateBoardDisplay();
       if (game.getBoardState().checkWin() == Mark.X) {
            showResult("X has won");
            System.out.println("X");
            gameOver = true;
       }
       else if (game.getBoardState().checkWin() == Mark.O) {
            System.out.println("O");
            gameOver = true;
            showResult("O has won");
       }
       if (game.getBoardState().checkWin() == Mark.TIE) {
        gameOver = true;
        showResult("Tie");
       }
        //checkpoint 3 modification
        //if game is simulated, instead of having a popup by calling showresult, start the next game if we have not run all the simulations
        
    }

    private void showResult(String result) {
        // Create an overlay to show the result. Include a button to play again. 
        Container<Label> titleLabel = Constants.createLabelWithBackgrounColor(result, Color.BLACK, skin);
        titleLabel.setBounds(150, 150, 150, 150);
        TextButton simulateButton = new TextButton("Play again?", skin);
        
        simulateButton.setBounds(115, 50, 300, 100);
        stage.addActor(simulateButton);;
        stage.addActor(titleLabel);
        // when the button is clicked, it should dissappear - you can do this using the .remove() command. \
        simulateButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resetGame();
                simulateButton.remove();
                titleLabel.remove();
            }
        });

        
    }
    public void resetGame() {
        //update board state, current player, etc. 
        game.setCurPlayer(0);
        board.reset();
        game.setBoardState(board);
        gameOver = false;
    }

    public void updateBoardDisplay() {//updates the board, you should call this if a move is made. No need to change. 
        boardTable.clearChildren();
        Mark[][] grid = game.getBoardState().getGrid();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Mark mark = grid[row][col];
                String text = "";
                if (mark == Mark.X) {
                    text = "X";
                } else if (mark == Mark.O) {
                    text = "O";
                }
                Label cellLabel = new Label(text, skin);
                cellLabel.setAlignment(Align.center);     // Center text within the label.
                cellLabel.setFontScale(5f); 
                boardTable.add(cellLabel).width(BOARD_WIDTH / 3).height(BOARD_HEIGHT / 3);
            }
            boardTable.row();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

        //checkpoint 3 - if it is not a humans turn, automate the AI's move here
        //call handleMoveMade afterwards
        
        if (!game.getCurPlayerObj().toString().equals("Human")) {
            if(gameOver == false) {
                Mark[][] grid = game.getBoardState().getGrid();
                int row = game.getCurPlayerObj().makeMove(board, game.getCurPlayerMark()).row;
                int col = game.getCurPlayerObj().makeMove(board, game.getCurPlayerMark()).col;
                grid[row][col] = game.getCurPlayerMark();
                game.nextPlayer();
                handleMoveMade();
            }
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
