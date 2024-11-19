package io.github.blackjack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MenuScreen implements Screen {
    private Main main;
    private TextButton startButton;
    private TextButton optionsButton;
    private TextButton quitButton;
    private Stage stage;
    private Skin skin;

    public MenuScreen(Main main, Skin skin) {
        this.main = main;
        this.skin = skin;
        this.stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        // Créer les boutons
        startButton = new TextButton("Commencer la partie", skin);
        optionsButton = new TextButton("Options", skin);
        quitButton = new TextButton("Quitter", skin);

        // Positionner les boutons
        startButton.setSize(200, 50);
        startButton.setPosition(Gdx.graphics.getWidth() / 2f - 100, Gdx.graphics.getHeight() / 2f + 60);

        optionsButton.setSize(200, 50);
        optionsButton.setPosition(Gdx.graphics.getWidth() / 2f - 100, Gdx.graphics.getHeight() / 2f);

        quitButton.setSize(200, 50);
        quitButton.setPosition(Gdx.graphics.getWidth() / 2f - 100, Gdx.graphics.getHeight() / 2f - 60);

        // Ajouter les boutons à la scène
        stage.addActor(startButton);
        stage.addActor(optionsButton);
        stage.addActor(quitButton);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.showGameScreen();
            }
        });

        // Listener pour le bouton "Quitter"
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
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