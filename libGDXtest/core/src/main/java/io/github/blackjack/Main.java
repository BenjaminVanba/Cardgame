package io.github.blackjack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Main extends Game {
    private Skin skin;
    private Music backgroundMusic;

    @Override
    public void create() {

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background_music.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        setScreen(new MenuScreen(this, this.skin));
    }

    public void showCasinoScreen() {
        setScreen(new CasinoScreen(this, this.skin));
    }

    public void showGameScreen() {
        setScreen(new GameScreen(this, this.skin));
    }

    public void SettingsScreen() {
        setScreen(new SettingsScreen(this, this.skin));
    }

    public Music getBackgroundMusic() {
        return backgroundMusic;
    }
}