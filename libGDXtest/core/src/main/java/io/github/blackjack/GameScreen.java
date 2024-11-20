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

public class GameScreen implements Screen {
    private Main main;
    private Stage stage;
    private Texture backgroundTexture;
    private SpriteBatch batch;
    private TextButton backButton, betButton, hitButton, standButton;
    private GameLogic gameLogic;
    private Label resultLabel; // Pour afficher le message

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
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'pause'");
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'resume'");
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'hide'");
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }
}
