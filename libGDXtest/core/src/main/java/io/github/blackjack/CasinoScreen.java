package io.github.blackjack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * La classe {@code CasinoScreen} représente l'écran du casino dans le jeu de
 * Blackjack.
 * Elle implémente l'interface {@link Screen} de libGDX, fournissant les
 * méthodes nécessaires
 * pour gérer l'affichage et les interactions utilisateur sur cet écran.
 * 
 * <p>
 * Cette classe est responsable de l'affichage des éléments graphiques du
 * casino, de la gestion
 * des interactions utilisateur telles que les clics, et de la mise à jour de
 * l'état de l'écran.
 * </p>
 * 
 * @see Screen
 * @see Main
 */
public class CasinoScreen implements Screen {
    /**
     * Référence à l'instance principale du jeu {@link Main}.
     */
    private Main main;

    /**
     * Skin utilisé pour l'interface utilisateur.
     */
    private Skin skin;

    /**
     * Texture de la première image du casino.
     */
    private Texture casino1Texture;

    /**
     * Texture de la deuxième image du casino.
     */
    private Texture casino2Texture;

    /**
     * Image représentant le premier élément graphique du casino.
     */
    private Image casino1Image;

    /**
     * Image représentant le deuxième élément graphique du casino.
     */
    private Image casino2Image;

    /**
     * Texture de l'arrière-plan de l'écran du casino.
     */
    private Texture backgroundTexture;

    /**
     * Stage de scène pour gérer les acteurs et les interactions.
     */
    private Stage stage;

    /**
     * SpriteBatch utilisé pour dessiner les sprites à l'écran.
     */
    private SpriteBatch batch;

    /**
     * Label affichant le titre de l'écran du casino.
     */
    private Label titleLabel;

    /**
     * Construit une nouvelle instance de {@code CasinoScreen} avec les paramètres
     * spécifiés.
     *
     * @param main référence à l'instance principale du jeu {@link Main}
     * @param skin skin utilisé pour l'interface utilisateur
     */
    public CasinoScreen(Main main, Skin skin) {
        this.main = main;
        this.skin = skin;
        this.stage = new Stage(new FitViewport(800, 480));
        Gdx.input.setInputProcessor(stage);
        this.batch = new SpriteBatch();
    }

    /**
     * Méthode appelée lorsque l'écran devient le rendu courant pour le jeu.
     * Initialise les éléments graphiques du casino et les écouteurs d'événements.
     */
    @Override
    public void show() {
        backgroundTexture = new Texture(Gdx.files.internal("Background2.png"));
        casino1Texture = new Texture(Gdx.files.internal("CasinoPourri.png"));
        casino2Texture = new Texture(Gdx.files.internal("CasinoPremium.png"));
        casino1Image = new Image(casino1Texture);
        casino2Image = new Image(casino2Texture);

        float imageWidth = 200;
        float imageHeight = 200;
        casino1Image.setSize(imageWidth, imageHeight);
        casino2Image.setSize(imageWidth, imageHeight);
        casino1Image.setOrigin(Align.center);
        casino2Image.setOrigin(Align.center);
        float separation = 50;
        float totalWidth = imageWidth * 2 + separation;
        float startX = (800 - totalWidth) / 2;
        float posY = (480 - imageHeight) / 2;
        casino1Image.setPosition(startX, posY);
        casino2Image.setPosition(startX + imageWidth + separation, posY);

        casino1Image.addListener(new ClickListener() {
            /**
             * Méthode appelée lors d'un clic sur la première image du casino.
             *
             * @param event l'événement d'entrée
             * @param x     la coordonnée x du clic
             * @param y     la coordonnée y du clic
             */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.showGameScreen();
            }
        });

        casino2Image.addListener(new ClickListener() {
            /**
             * Méthode appelée lors d'un clic sur la deuxième image du casino.
             *
             * @param event l'événement d'entrée
             * @param x     la coordonnée x du clic
             * @param y     la coordonnée y du clic
             */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.showGameScreen();
            }
        });

        casino1Image.addListener(new InputListener() {
            /**
             * Méthode appelée lorsque le curseur entre dans la première image du casino.
             *
             * @param event     l'événement d'entrée
             * @param x         la coordonnée x de l'entrée
             * @param y         la coordonnée y de l'entrée
             * @param pointer   l'indice du pointeur
             * @param fromActor l'acteur précédent
             */
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                casino1Image.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            /**
             * Méthode appelée lorsque le curseur quitte la première image du casino.
             *
             * @param event   l'événement d'entrée
             * @param x       la coordonnée x de la sortie
             * @param y       la coordonnée y de la sortie
             * @param pointer l'indice du pointeur
             * @param toActor l'acteur suivant
             */
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                casino1Image.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }
        });

        casino2Image.addListener(new InputListener() {
            /**
             * Méthode appelée lorsque le curseur entre dans la deuxième image du casino.
             *
             * @param event     l'événement d'entrée
             * @param x         la coordonnée x de l'entrée
             * @param y         la coordonnée y de l'entrée
             * @param pointer   l'indice du pointeur
             * @param fromActor l'acteur précédent
             */
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                casino2Image.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            /**
             * Méthode appelée lorsque le curseur quitte la deuxième image du casino.
             *
             * @param event   l'événement d'entrée
             * @param x       la coordonnée x de la sortie
             * @param y       la coordonnée y de la sortie
             * @param pointer l'indice du pointeur
             * @param toActor l'acteur suivant
             */
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                casino2Image.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }
        });

        titleLabel = new Label("Choisissez votre casino :", skin);

        float labelX = 800 / 2f;
        float labelY = posY + imageHeight + 20;
        titleLabel.setPosition(labelX, labelY, Align.center);

        stage.addActor(titleLabel);
        stage.addActor(casino1Image);
        stage.addActor(casino2Image);
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
     * Libère les ressources utilisées par l'écran.
     */
    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        backgroundTexture.dispose();
        casino1Texture.dispose();
        casino2Texture.dispose();
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
}