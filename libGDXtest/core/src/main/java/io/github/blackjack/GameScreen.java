package io.github.blackjack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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

    private TextButton backButton, betButton, hitButton, standButton;
    private GameLogic gameLogic;
    private Label resultLabel; // Pour afficher le message

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
        this.stage = new Stage(new FitViewport(1960, 1080));
        Gdx.input.setInputProcessor(stage);
        this.batch = new SpriteBatch();

        backButton = new TextButton("Retour au menu", skin);

        backButton.setSize(150, 50);
        backButton.setPosition(1960 - backButton.getWidth() - 10, 1080 - backButton.getHeight() - 10);
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


        betButton = new TextButton("Mise", skin);
        betButton.setSize(150, 50);
        betButton.setPosition(10, 10);
        stage.addActor(betButton);

        hitButton = new TextButton("Distribuer", skin);
        hitButton.setSize(150, 50);
        hitButton.setPosition(200, 10);
        stage.addActor(hitButton);

        standButton = new TextButton("Rester", skin);
        standButton.setSize(150, 50);
        standButton.setPosition(400, 10);
        stage.addActor(standButton);

        gameLogic = new GameLogic(stage);
        stage.addActor(gameLogic);

        resultLabel = new Label("", skin);
        resultLabel.setPosition(1960 / 2 - 100, 1080 / 2);
        stage.addActor(resultLabel);

        betButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameLogic.distributeInitialCards();
                resultLabel.setText("");
            }
        });

        hitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameLogic.playerHits();
                resultLabel.setText(gameLogic.getResultMessage());
            }
        });

        standButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameLogic.playerStands();
                resultLabel.setText(gameLogic.getResultMessage());
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
    public void show() {
        // Charger l'arrière-plan si ce n'est pas déjà fait
        if (backgroundTexture == null) {
            backgroundTexture = new Texture(Gdx.files.internal("Background.jpg"));
        }

        // Vous pouvez initialiser ici d'autres ressources ou éléments spécifiques au
        // moment
        // où cet écran devient actif
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'resize'");
        stage.getViewport().update(width, height, true);
    }

    /**
     * Méthode appelée lorsque le jeu est mis en pause.
     */
    @Override
    public void pause() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'pause'");
        // Implémentation de la méthode pause() si nécessaire
    }

    /**
     * Méthode appelée lorsque le jeu est repris après une pause.
     */
    @Override
    public void resume() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'resume'");
        // Implémentation de la méthode resume() si nécessaire
    }

    /**
     * Méthode appelée lorsque l'écran n'est plus le rendu courant pour le jeu.
     */
    @Override
    public void hide() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'hide'");
        // Implémentation de la méthode hide() si nécessaire
    }

    /**
     * Libère les ressources utilisées par l'écran.
     */
    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }
}
