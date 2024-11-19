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

public class GameScreen implements Screen {
    private Main main;
    private Stage stage;
    private Texture cardTexture;
    private Texture backgroundTexture;
    private Image cardImage;
    private SpriteBatch batch;
    private TextButton backButton;

    public GameScreen(Main main, Skin skin) {
        this.main = main;
        this.stage = new Stage(new FitViewport(800, 480));
        Gdx.input.setInputProcessor(stage);
        this.batch = new SpriteBatch();

        // Créer le bouton "Retour au menu"
        backButton = new TextButton("Retour au menu", skin);

        // Positionner le bouton en haut à droite de l'écran
        backButton.setSize(150, 50);
        backButton.setPosition(800 - backButton.getWidth() - 10, 480 - backButton.getHeight() - 10);

        // Ajouter le bouton à la scène
        stage.addActor(backButton);

        // Ajouter un listener au bouton pour revenir au menu principal
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new MenuScreen(main, skin));
            }
        });
    }

    @Override
    public void show() {
        backgroundTexture = new Texture(Gdx.files.internal("Background.jpg"));
        cardTexture = new Texture(Gdx.files.internal("Cards/Clovers_2_white.png"));
        cardImage = new Image(cardTexture);

        // Ajuster la taille de l'image pour qu'elle fasse un quart de l'écran
        float cardWidth = 100; // Par exemple, 1/4 de la largeur de l'écran
        float cardHeight = 200; // Par exemple, 1/2 de la hauteur de l'écran
        cardImage.setSize(cardWidth, cardHeight);

        // Positionner la carte au centre de l'écran
        cardImage.setPosition((800 - cardWidth) / 2, (480 - cardHeight) / 2);

        // Ajouter la carte à la scène
        stage.addActor(cardImage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Dessiner l'arrière-plan
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Mettre à jour et dessiner la scène
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        // Recalculer la position du bouton pour qu'il reste en haut à droite
        // backButton.setPosition(width - backButton.getWidth() - 10, height -
        // backButton.getHeight() - 10);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        cardTexture.dispose();
        backgroundTexture.dispose();
        batch.dispose();
    }
}