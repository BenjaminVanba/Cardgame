package io.github.blackjack;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Menu extends Window {

    private TextButton optionsButton;

    public Menu(Skin skin) {
        super("Menu", skin, "border");
        setupMenu(skin);
    }

    private void setupMenu(Skin skin) {
        defaults().pad(10f);
        add("Bienvenue dans le jeu!").row();

        TextButton startButton = new TextButton("Commencer la partie", skin);
        optionsButton = new TextButton("Options", skin); // Ajout du bouton Options
        TextButton quitButton = new TextButton("Quitter", skin);

        add(startButton).row();
        add(optionsButton).row();
        add(quitButton).row();

        pack();
        setPosition(MathUtils.roundPositive(getWidth() / 2f - getWidth() / 2f),
                MathUtils.roundPositive(getHeight() / 2f - getHeight() / 2f));
    }

    public TextButton getOptionsButton() {
        return optionsButton; // Getter pour le bouton Options
    }
}
