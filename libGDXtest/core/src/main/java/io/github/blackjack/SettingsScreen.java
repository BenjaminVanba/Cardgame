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

public class SettingsScreen implements Screen {
    private Main main;
    private Texture backgroundTexture;
    private Stage stage;
    private SpriteBatch batch;
    private Skin skin;
    private Slider volumeSlider;
    private TextButton backButton;

    public SettingsScreen(Main main, Skin skin) {
        this.main = main;
        this.skin = skin;
        this.stage = new Stage(new FitViewport(800, 480));
        Gdx.input.setInputProcessor(stage);
        this.batch = new SpriteBatch();
    }

    @Override
    public void show() {
        backgroundTexture = new Texture(Gdx.files.internal("Background2.png"));

        // Créer un slider de volume
        volumeSlider = new Slider(0, 1, 0.01f, false, skin);
        volumeSlider.setValue(main.getBackgroundMusic().getVolume()); // Initialiser le slider avec le volume actuel

        // Ajouter un listener au slider pour ajuster le volume de la musique de fond
        volumeSlider.addListener(event -> {
            main.getBackgroundMusic().setVolume(volumeSlider.getValue());
            return false;
        });

        // Créer un label "Volume"
        Label volumeLabel = new Label("Volume", skin);

        // Créer un bouton "Retour"
        backButton = new TextButton("Retour", skin);

        // Ajouter un listener au bouton "Retour" pour revenir au menu principal
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                main.setScreen(new MenuScreen(main, skin));
            }
        });

        // Ajouter les éléments à la scène
        Table table = new Table();
        table.setFillParent(true);
        table.add(volumeLabel).padBottom(10);
        table.row();
        table.add(volumeSlider).width(400).padBottom(20);
        table.row();
        table.add(backButton).width(200).height(50);
        stage.addActor(table);
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
}