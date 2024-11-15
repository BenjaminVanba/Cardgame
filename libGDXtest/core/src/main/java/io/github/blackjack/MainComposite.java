package io.github.blackjack;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MainComposite extends Actor {
    private Menu menu;
    private Option optionWindow;

    public MainComposite(Skin skin) {
        // Initialiser les composants
        menu = new Menu(skin);
        optionWindow = new Option(skin);

        // Définir la fenêtre d'options comme invisible au départ
        optionWindow.setVisible(false);

        // Listener pour afficher la fenêtre d'options quand on clique sur le bouton
        // "Options"
        menu.getOptionsButton().addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                showOptions();
            }
        });

        // Listener pour revenir au menu principal depuis les options
        optionWindow.getReturnButton().addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                showMenu();
            }
        });
    }

    // Ajouter les composants au stage
    public void render(Stage stage) {
        stage.addActor(menu);
        stage.addActor(optionWindow);

        // Centrer le menu après l'avoir ajouté au stage
        centerMenu(stage);
    }

    // Centrer le menu sur l'écran
    private void centerMenu(Stage stage) {
        float menuX = (stage.getWidth() - menu.getWidth()) / 2;
        float menuY = (stage.getHeight() - menu.getHeight()) / 2;
        menu.setPosition(menuX, menuY);
    }

    // Afficher le menu principal et masquer les options
    public void showMenu() {
        menu.setVisible(true);
        optionWindow.setVisible(false);
    }

    // Afficher les options et masquer le menu principal
    public void showOptions() {
        optionWindow.setVisible(true);
        menu.setVisible(false);

        // Centrer la fenêtre d'options
        centerOptionWindow(optionWindow.getStage());
    }

    // Centrer la fenêtre d'options
    private void centerOptionWindow(Stage stage) {
        float optionX = (stage.getWidth() - optionWindow.getWidth()) / 2;
        float optionY = (stage.getHeight() - optionWindow.getHeight()) / 2;
        optionWindow.setPosition(optionX, optionY);
    }
}
