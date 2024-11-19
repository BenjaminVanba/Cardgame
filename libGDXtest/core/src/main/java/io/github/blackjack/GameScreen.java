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

        backButton = new TextButton("Retour au menu", skin);

        backButton.setSize(150, 50);
        backButton.setPosition(800 - backButton.getWidth() - 10, 480 - backButton.getHeight() - 10);

        stage.addActor(backButton);

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

        float cardWidth = 100;
        float cardHeight = 200;
        cardImage.setSize(cardWidth, cardHeight);

        cardImage.setPosition((800 - cardWidth) / 2, (480 - cardHeight) / 2);

        stage.addActor(cardImage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

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
        backgroundTexture.dispose();
        batch.dispose();
    }
}