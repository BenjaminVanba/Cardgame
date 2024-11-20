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

public class CasinoScreen implements Screen {
    private Main main;
    private Texture casino1Texture;
    private Texture casino2Texture;
    private Image casino1Image;
    private Image casino2Image;
    private Texture backgroundTexture;
    private Stage stage;
    private SpriteBatch batch;

    public CasinoScreen(Main main, Skin skin) {
        this.main = main;
        this.stage = new Stage(new FitViewport(800, 480));
        Gdx.input.setInputProcessor(stage);
        this.batch = new SpriteBatch();
    }

    @Override
    public void show() {
        // Load the background texture
        backgroundTexture = new Texture(Gdx.files.internal("Background2.png"));

        // Load casino images
        casino1Texture = new Texture(Gdx.files.internal("CasinoPourri.png"));
        casino2Texture = new Texture(Gdx.files.internal("CasinoPremium.png"));

        // Create Image actors
        casino1Image = new Image(casino1Texture);
        casino2Image = new Image(casino2Texture);

        // Set sizes for the images
        float imageWidth = 200;
        float imageHeight = 200;
        casino1Image.setSize(imageWidth, imageHeight);
        casino2Image.setSize(imageWidth, imageHeight);

        // Set origin to center for scaling
        casino1Image.setOrigin(Align.center);
        casino2Image.setOrigin(Align.center);

        // Position images side by side with a separation
        float separation = 50;
        float totalWidth = imageWidth * 2 + separation;
        float startX = (800 - totalWidth) / 2;
        float posY = (480 - imageHeight) / 2;

        casino1Image.setPosition(startX, posY);
        casino2Image.setPosition(startX + imageWidth + separation, posY);

        // Add click listeners to images
        casino1Image.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.showGameScreen();
            }
        });

        casino2Image.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.showGameScreen();
            }
        });

        // Add hover effect to images
        casino1Image.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                casino1Image.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                casino1Image.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }
        });

        casino2Image.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                casino2Image.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                casino2Image.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }
        });

        // Add images to the stage
        stage.addActor(casino1Image);
        stage.addActor(casino2Image);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Update and draw the stage
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        backgroundTexture.dispose();
        casino1Texture.dispose();
        casino2Texture.dispose();
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
}