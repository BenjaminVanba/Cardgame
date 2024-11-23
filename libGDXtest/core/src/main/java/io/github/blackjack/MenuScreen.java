package io.github.blackjack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * La classe {@code MenuScreen} représente l'écran principal du menu dans le jeu
 * de Blackjack.
 * Elle implémente l'interface {@link Screen} de libGDX, fournissant les
 * méthodes nécessaires
 * pour gérer l'affichage et les interactions utilisateur sur cet écran.
 * 
 * <p>
 * Cette classe est responsable de l'affichage des options du menu telles que
 * commencer la partie,
 * accéder aux options ou quitter le jeu, ainsi que de la gestion des
 * interactions utilisateur.
 * </p>
 * 
 * @see Screen
 * @see Main
 * @see TextButton
 */
public class MenuScreen implements Screen {
    /**
     * Référence à l'instance principale du jeu {@link Main}.
     */
    private Main main;

    /**
     * Bouton permettant de démarrer une nouvelle partie.
     */
    private TextButton startButton;

    /**
     * Bouton permettant d'accéder aux options du jeu.
     */
    private TextButton optionsButton;

    /**
     * Bouton permettant de quitter le jeu.
     */
    private TextButton quitButton;

    /**
     * Texture de l'arrière-plan de l'écran principal du menu.
     */
    private Texture backgroundTexture;

    /**
     * Stage de scène pour gérer les acteurs et les interactions utilisateur.
     */
    private Stage stage;

    /**
     * SpriteBatch utilisé pour dessiner les sprites à l'écran.
     */
    private SpriteBatch batch;

    /**
     * Skin utilisé pour l'interface utilisateur.
     */
    private Skin skin;

    /**
     * Construit une nouvelle instance de {@code MenuScreen} avec les paramètres
     * spécifiés.
     *
     * @param main référence à l'instance principale du jeu {@link Main}
     * @param skin skin utilisé pour l'interface utilisateur
     */
    public MenuScreen(Main main, Skin skin) {
        this.main = main;
        this.skin = skin;
        this.stage = new Stage(new FitViewport(1960, 1080));
        Gdx.input.setInputProcessor(stage);
        this.batch = new SpriteBatch();
    }

    /**
     * Méthode appelée lorsque l'écran devient le rendu courant pour le jeu.
     * Initialise les éléments graphiques du menu et les écouteurs d'événements.
     */
    @Override
    public void show() {
        Gdx.app.log("MenuScreen", "Méthode show() appelée");

        // Charger l'arrière-plan
        backgroundTexture = new Texture(Gdx.files.internal("Background2.png"));

        // Créer les boutons
        startButton = new TextButton("Commencer la partie", skin);
        optionsButton = new TextButton("Options", skin);
        quitButton = new TextButton("Quitter", skin);

        // Définir la taille des boutons
        float buttonWidth = 200f;
        float buttonHeight = 50f;
        startButton.setSize(buttonWidth, buttonHeight);
        optionsButton.setSize(buttonWidth, buttonHeight);
        quitButton.setSize(buttonWidth, buttonHeight);

        // Calculer les positions centrées
        float screenWidth = 1960f;
        float screenHeight = 1080f;

        float totalHeight = 3 * buttonHeight + 2 * 20f; // 190
        float startY = (screenHeight + totalHeight) / 2 - buttonHeight; // 585
        float centerX = (screenWidth / 2) - (buttonWidth / 2); // 880

        // Positionner les boutons
        startButton.setPosition(centerX, startY); // 880, 585
        optionsButton.setPosition(centerX, startY - buttonHeight - 20f); // 880, 515
        quitButton.setPosition(centerX, startY - 2 * (buttonHeight + 20f)); // 880, 445

        // Ajouter les boutons au stage
        stage.addActor(startButton);
        stage.addActor(optionsButton);
        stage.addActor(quitButton);

        // Ajouter les listeners pour les boutons
        startButton.addListener(new ClickListener() {
            /**
             * Méthode appelée lors d'un clic sur le bouton de démarrage de la partie.
             *
             * @param event l'événement d'entrée
             * @param x     la coordonnée x du clic
             * @param y     la coordonnée y du clic
             */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.showCasinoScreen();
            }
        });

        optionsButton.addListener(new ClickListener() {
            /**
             * Méthode appelée lors d'un clic sur le bouton des options.
             *
             * @param event l'événement d'entrée
             * @param x     la coordonnée x du clic
             * @param y     la coordonnée y du clic
             */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.SettingsScreen(); // Correction de la méthode (majuscule en minuscule)
            }
        });

        quitButton.addListener(new ClickListener() {
            /**
             * Méthode appelée lors d'un clic sur le bouton de sortie du jeu.
             *
             * @param event l'événement d'entrée
             * @param x     la coordonnée x du clic
             * @param y     la coordonnée y du clic
             */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    /**
     * Méthode appelée une fois par frame pour dessiner l'écran.
     *
     * @param delta le temps écoulé depuis la dernière frame en secondes
     */
    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    /**
     * Méthode appelée lorsqu'il y a un changement de taille de l'écran.
     *
     * @param width  la nouvelle largeur de l'écran
     * @param height la nouvelle hauteur de l'écran
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /**
     * Méthode appelée lorsque le jeu est mis en pause.
     */
    @Override
    public void pause() {
        // Implémentation de la méthode pause() si nécessaire
    }

    /**
     * Méthode appelée lorsque le jeu est repris après une pause.
     */
    @Override
    public void resume() {
        // Implémentation de la méthode resume() si nécessaire
    }

    /**
     * Méthode appelée lorsque l'écran n'est plus le rendu courant pour le jeu.
     */
    @Override
    public void hide() {
        // Implémentation de la méthode hide() si nécessaire
    }

    /**
     * Libère les ressources utilisées par l'écran.
     */
    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        backgroundTexture.dispose();
        batch.dispose();
    }

    /**
     * Retourne le bouton de démarrage.
     *
     * @return le bouton de démarrage
     */
    public TextButton getStartButton() {
        return startButton;
    }

    /**
     * Retourne le bouton des options.
     *
     * @return le bouton des options
     */
    public TextButton getOptionsButton() {
        return optionsButton;
    }

    /**
     * Retourne le bouton de quitter.
     *
     * @return le bouton de quitter
     */
    public TextButton getQuitButton() {
        return quitButton;
    }
}