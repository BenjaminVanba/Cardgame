package io.github.blackjack;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Option extends Window {
    private TextButton returnButton;

    public Option(Skin skin) {
        super("Menu des options", skin, "border");
        setupOptions(skin);
    }

    private void setupOptions(Skin skin) {
        Label congratulationsLabel = new Label("Félicitations, ceci est le menu option", skin);
        returnButton = new TextButton("Retour", skin);

        add(congratulationsLabel).row();
        add(returnButton).padTop(10f).row();

        pack();
    }

    public TextButton getReturnButton() {
        return returnButton; // Getter pour le bouton Retour
    }
}
