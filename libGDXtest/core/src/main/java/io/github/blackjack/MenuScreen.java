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

public class MenuScreen implements Screen {
    private Main main;
    private TextButton startButton;
    private TextButton optionsButton;
    private TextButton quitButton;
    private Texture backgroundTexture;
    private Stage stage;
    private SpriteBatch batch;
    private Skin skin;

    public MenuScreen(Main main, Skin skin) {
        this.main = main;
        this.skin = skin;
        this.stage = new Stage(new FitViewport(800, 480));
        Gdx.input.setInputProcessor(stage);
        this.batch = new SpriteBatch();
    }

    @Override
    public void show() {
        backgroundTexture = new Texture(Gdx.files.internal("Background2.png"));

        startButton = new TextButton("Commencer la partie", skin);
        optionsButton = new TextButton("Options", skin);
        quitButton = new TextButton("Quitter", skin);

        startButton.setSize(200, 50);
        startButton.setPosition(300, 270);

        optionsButton.setSize(200, 50);
        optionsButton.setPosition(300, 210);

        quitButton.setSize(200, 50);
        quitButton.setPosition(300, 150);

        stage.addActor(startButton);
        stage.addActor(optionsButton);
        stage.addActor(quitButton);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.showGameScreen();
            }
        });

        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.SettingsScreen();
            }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

    }

    @Override
    public void render(float delta) {
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
        skin.dispose();
        backgroundTexture.dispose();
        batch.dispose();
    }

    public TextButton getStartButton() {
        return startButton;
    }

    public TextButton getOptionsButton() {
        return optionsButton;
    }

    public TextButton getQuitButton() {
        return quitButton;
    }
}