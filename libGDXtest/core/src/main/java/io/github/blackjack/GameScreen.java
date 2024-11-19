package io.github.blackjack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen implements Screen {
    private Main main;
    private Stage stage;
    private Texture cardTexture;
    private Image cardImage;

    public GameScreen(Main main, Skin skin) {
        this.main = main;
        this.stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    }

    @Override
    public void show() {
        cardTexture = new Texture(Gdx.files.internal("Cards/Clovers_2_white.png"));
        cardImage = new Image(cardTexture);

        // Ajuster la taille de l'image pour qu'elle fasse la moitié de l'écran
        float cardWidth = Gdx.graphics.getWidth() / 2; // Par exemple, 1/2 de la largeur de l'écran
        float cardHeight = Gdx.graphics.getHeight() / 2; // Par exemple, 1/2 de la hauteur de l'écran
        cardImage.setSize(cardWidth, cardHeight);

        // Positionner la carte au centre de l'écran
        cardImage.setPosition((Gdx.graphics.getWidth() - cardWidth) / 2, (Gdx.graphics.getHeight() - cardHeight) / 2);

        // Ajouter la carte à la scène
        stage.addActor(cardImage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
    }
}