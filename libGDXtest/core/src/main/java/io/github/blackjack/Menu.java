package io.github.blackjack;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Menu extends Window {

    public Menu(Skin skin) {
        super("Menu", skin, "border");
        setupMenu(skin);
    }

    private void setupMenu(Skin skin) {
        defaults().pad(10f);
        add("Bienvenue dans le jeu!").row();

        TextButton startButton = new TextButton("Commencer la partie", skin);
        TextButton optionsButton = new TextButton("Options", skin);
        TextButton quitButton = new TextButton("Quitter", skin);

        add(startButton).row();
        add(optionsButton).row();
        add(quitButton).row();

        pack();
    }

    public void centerMenu(Stage stage) {
        // Calculer le centre de la scène et positionner le menu en conséquence
        float x = (stage.getWidth() - getWidth()) / 2f;
        float y = (stage.getHeight() - getHeight()) / 2f;
        setPosition(x, y);
    }
}
