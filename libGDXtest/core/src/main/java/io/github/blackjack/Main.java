package io.github.blackjack;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Main extends ApplicationAdapter {
    private Stage stage;
    private Skin skin;
    private Texture backgroundTexture;
    private SpriteBatch batch;
    private MainComposite mainComposite;
    private Menu menu;
    private Option optionWindow;

    @Override
    public void create() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture("Background.jpg");

        stage = new Stage(new FitViewport(800, 480));
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        // Créer le composite principal
        mainComposite = new MainComposite();

        // Créer le menu
        menu = new Menu(skin);
        mainComposite.addComponent(menu);

        // Créer la fenêtre des options (invisible au départ)
        optionWindow = new Option(skin);
        optionWindow.setVisible(false); // La fenêtre des options est cachée au départ
        mainComposite.addComponent(optionWindow); // Ajouter la fenêtre des options au composite

        // Ajouter un listener au bouton "Options" pour afficher la fenêtre des options
        menu.getOptionsButton().addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                optionWindow.setVisible(true); // Afficher la fenêtre des options
                optionWindow.setPosition(
                        (stage.getWidth() - optionWindow.getWidth()) / 2,
                        (stage.getHeight() - optionWindow.getHeight()) / 2); // Centrer la fenêtre des options sur
                                                                             // l'écran

                // Masquer le menu
                menu.setVisible(false);
            }
        });

        // Ajouter un listener au bouton "Retour" des options pour revenir au menu
        optionWindow.getReturnButton().addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                optionWindow.setVisible(false); // Masquer la fenêtre des options
                menu.setVisible(true); // Afficher de nouveau le menu
            }
        });

        // Centrer le menu
        float menuX = (stage.getWidth() - menu.getWidth()) / 2;
        float menuY = (stage.getHeight() - menu.getHeight()) / 2;
        menu.setPosition(menuX, menuY);

        // Ajouter le composite au stage
        mainComposite.render(stage);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        batch.dispose();
        backgroundTexture.dispose();
    }
}
