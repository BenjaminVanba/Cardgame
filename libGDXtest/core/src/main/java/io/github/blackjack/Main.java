package io.github.blackjack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Main extends Game {
    private Skin skin;
    // private Settings settings;

    @Override
    public void create() {
        this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        setScreen(new MenuScreen(this, this.skin));
    }

    public void showGameScreen() {
        setScreen(new GameScreen(this, this.skin)); // , settings));
    }

    // public void setSettings(Settings settings) {
    // this.settings = settings;
    // }
}