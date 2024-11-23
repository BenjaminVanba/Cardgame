package io.github.blackjack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * La classe {@code SettingsScreen} représente l'écran des paramètres dans le
 * jeu de Blackjack.
 * Elle implémente l'interface {@link Screen} de libGDX, fournissant les
 * méthodes nécessaires
 * pour gérer l'affichage et les interactions utilisateur sur cet écran.
 * 
 * <p>
 * Cette classe est responsable de l'affichage des options de réglage telles que
 * le volume,
 * et de la gestion des interactions utilisateur telles que le retour au menu
 * principal.
 * </p>
 * 
 * @see Screen
 * @see Main
 * @see TextButton
 * @see Slider
 */
public class SettingsScreen implements Screen {
    /**
     * Référence à l'instance principale du jeu {@link Main}.
     */
    private Main main;

    /**
     * Texture de l'arrière-plan de l'écran des paramètres.
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
     * Slider permettant de régler le volume de la musique de fond.
     */
    private Slider volumeSlider;

    /**
     * Bouton permettant de revenir à l'écran principal du menu.
     */
    private TextButton backButton;

    /**
     * Construit une nouvelle instance de {@code SettingsScreen} avec les paramètres
     * spécifiés.
     *
     * @param main référence à l'instance principale du jeu {@link Main}
     * @param skin skin utilisé pour l'interface utilisateur
     */
    public SettingsScreen(Main main, Skin skin) {
        this.main = main;
        this.skin = skin;
        this.stage = new Stage(new FitViewport(1960, 1080));
        Gdx.input.setInputProcessor(stage);
        this.batch = new SpriteBatch();
    }

    /**
     * Méthode appelée lorsque l'écran devient le rendu courant pour le jeu.
     * Initialise les éléments graphiques des paramètres et les écouteurs
     * d'événements.
     */
    @Override
    public void show() {
        backgroundTexture = new Texture(Gdx.files.internal("Background2.png"));

        volumeSlider = new Slider(0, 1, 0.01f, false, skin);
        volumeSlider.setValue(main.getBackgroundMusic().getVolume());

        volumeSlider.addListener(event -> {
            main.getBackgroundMusic().setVolume(volumeSlider.getValue());
            return false;
        });

        Label volumeLabel = new Label("Volume", skin);

        backButton = new TextButton("Retour", skin);

        backButton.addListener(new ClickListener() {
            /**
             * Méthode appelée lors d'un clic sur le bouton de retour.
             *
             * @param event l'événement d'entrée
             * @param x     la coordonnée x du clic
             * @param y     la coordonnée y du clic
             */
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                main.setScreen(new MenuScreen(main, skin));
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.add(volumeLabel).padBottom(10);
        table.row();
        table.add(volumeSlider).width(400).padBottom(20);
        table.row();
        table.add(backButton).width(200).height(50);
        stage.addActor(table);
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
        skin.dispose();
        backgroundTexture.dispose();
        batch.dispose();
    }
}