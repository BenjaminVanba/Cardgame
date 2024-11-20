package io.github.blackjack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * La classe {@code GameScreen} représente l'écran de jeu principal dans le jeu
 * de Blackjack.
 * Elle implémente l'interface {@link Screen} de libGDX, fournissant les
 * méthodes nécessaires
 * pour gérer l'affichage et les interactions utilisateur sur cet écran.
 * 
 * <p>
 * Cette classe est responsable de l'affichage des cartes, de la gestion des
 * interactions
 * utilisateur telles que le retour au menu, et de la mise à jour de l'état de
 * l'écran de jeu.
 * </p>
 * 
 * @see Screen
 * @see Main
 * @see MenuScreen
 */
public class GameScreen implements Screen {
    /**
     * Référence à l'instance principale du jeu {@link Main}.
     */
    private Main main;

    /**
     * Stage de scène pour gérer les acteurs et les interactions utilisateur.
     */
    private Stage stage;

    /**
     * Texture de la carte affichée à l'écran.
     */
    private Texture cardTexture;

    /**
     * Image représentant la carte affichée à l'écran.
     */
    private Image cardImage;

    /**
     * Texture de l'arrière-plan de l'écran de jeu.
     */
    private Texture backgroundTexture;

    /**
     * SpriteBatch utilisé pour dessiner les sprites à l'écran.
     */
    private SpriteBatch batch;

    /**
     * Bouton permettant de revenir au menu principal.
     */
    private TextButton backButton;

    /**
     * Construit une nouvelle instance de {@code GameScreen} avec les paramètres
     * spécifiés.
     *
     * @param main référence à l'instance principale du jeu {@link Main}
     * @param skin skin utilisé pour l'interface utilisateur
     */
    public GameScreen(Main main, Skin skin) {
        this.main = main;
        this.stage = new Stage(new FitViewport(800, 480));
        Gdx.input.setInputProcessor(stage);
        this.batch = new SpriteBatch();

        backButton = new TextButton("Retour au menu", skin);

        backButton.setSize(150, 50);
        backButton.setPosition(800 - backButton.getWidth() - 10, 480 - backButton.getHeight() - 10);

        stage.addActor(backButton);

        backButton.addListener(new ClickListener() {
            /**
             * Méthode appelée lors d'un clic sur le bouton de retour au menu.
             *
             * @param event l'événement d'entrée
             * @param x     la coordonnée x du clic
             * @param y     la coordonnée y du clic
             */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new MenuScreen(main, skin));
            }
        });
    }

    /**
     * Méthode appelée lorsque l'écran devient le rendu courant pour le jeu.
     * Initialise les éléments graphiques du jeu et les écouteurs d'événements.
     */
    @Override
    public void show() {
        backgroundTexture = new Texture(Gdx.files.internal("Background.jpg"));
        cardTexture = new Texture(Gdx.files.internal("Cards/Clovers_2_white.png"));
        cardImage = new Image(cardTexture);

        float cardWidth = 100;
        float cardHeight = 200;
        cardImage.setSize(cardWidth, cardHeight);

        cardImage.setPosition((800 - cardWidth) / 2, (480 - cardHeight) / 2);

        stage.addActor(cardImage);
    }

    /**
     * Méthode appelée une fois par frame pour dessiner l'écran.
     *
     * @param delta le temps écoulé depuis la dernière frame en secondes
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        cardTexture.dispose();
        backgroundTexture.dispose();
        batch.dispose();
    }
}