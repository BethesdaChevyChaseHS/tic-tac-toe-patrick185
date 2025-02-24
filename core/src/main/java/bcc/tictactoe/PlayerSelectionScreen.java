package bcc.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PlayerSelectionScreen extends ScreenAdapter{
    private final TicTacToe game;
    private Stage stage;
    private Skin skin;

    public PlayerSelectionScreen(TicTacToe game, int curPlayer) {//checkpoint 1
        this.game = game;
        
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
       //load skin
        skin = new Skin(Gdx.files.internal("skins/glassy/glassy-ui.json"));
   
       //add title saying something like "select player"
       Label title = new Label("Select Player 1", skin);
       title.setPosition(125, 450);
       
       //if you would like a background color behind the title, you can use the helper method in the Constants file
       Texture backgroundTexture = new Texture(Gdx.files.internal("space_tictactoe.png"));
       TextureRegionDrawable backgroundDrawable =
                new TextureRegionDrawable(new TextureRegion(backgroundTexture));

        
       //check out the documentation linked in the readme / on canvas
        TicTacToe test = new TicTacToe();
        
       //add buttons to select from the player types listed in constants.java. If there isSimulated is true, don't let human be an option. 
       TextButton button1 = new TextButton("Random AI", skin);
       button1.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                    test.setPlayer(curPlayer, "Random AI");
                    
            } 
       });
       button1.setBounds(35, 335, 400, 100);
       
       TextButton button3 = new TextButton("Slightly Smart AI", skin);
       button3.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                test.setPlayer(curPlayer, "Slightly Smart AI");
                
            } 
       });
       TextButton button4 = new TextButton("Smart AI", skin);
       button4.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                test.setPlayer(curPlayer, "Smart AI");

            } 
       });
       button4.setPosition(90, 6);
       button3.setPosition(5, 225);
       
       
       
       //curplayer will either be 0 or 1
       Table table = new Table();
       table.setFillParent(true);
        table.center();
        table.add(title).pad(5).row();
        table.add(button1).pad(5).row();
        table.add(button3).pad(5).row();
        table.add(button4).pad(5).row();
        table.setBackground(backgroundDrawable);
       

        if (game.getIsSimulated()==false) {
            TextButton button2 = new TextButton("Human", skin);
            button2.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                test.setPlayer(curPlayer, "Human");
                
            } 
            
       });
       table.add(button2).pad(5).row();;
       }

        stage.addActor(table);

       
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
