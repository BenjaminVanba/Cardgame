package io.github.blackjack;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Option extends Window {

    private TextButton returnButton;

    public Option(Skin skin) {
        super("Menu des options", skin, "border");
        setupOptions(skin);
    }

    private void setupOptions(Skin skin) {
        Label congratulationsLabel = new Label("Félicitations, ceci est le menu option", skin);
        add(congratulationsLabel).row(); // Ajouter le texte à la fenêtre

        returnButton = new TextButton("Retour", skin);
        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setVisible(false); // Masquer la fenêtre des options
            }
        });

        add(returnButton).row();

        pack(); // Adapter la taille de la fenêtre au contenu
    }

    public TextButton getReturnButton() {
        return returnButton; // Getter pour le bouton Retour
    }
}
