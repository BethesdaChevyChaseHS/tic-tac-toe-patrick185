package bcc.tictactoe;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public final class Constants {
    public static final String[] PLAYER_OPTIONS = { "Human", "Random AI", "Slightly Smart AI", "Smart AI" };

    public static Container<Label> createLabelWithBackgrounColor(String text, Color bgColor, Skin skin) {
        // Create the label using the provided skin.
        Label label = new Label(text, skin);

        // Create a 1x1 Pixmap and fill it with the background color.
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(bgColor);
        pixmap.fill();

        // Convert the Pixmap to a Texture and dispose of the Pixmap.
        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        // Create a TextureRegionDrawable from the texture.
        TextureRegionDrawable backgroundDrawable = new TextureRegionDrawable(new TextureRegion(texture));

        // Wrap the Label in a Container and set its background.
        Container<Label> container = new Container<Label>(label);
        container.setBackground(backgroundDrawable);
        container.pad(10); // Optional: add padding around the label.

        return container;
    }
}
