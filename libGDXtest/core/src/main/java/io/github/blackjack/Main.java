package io.github.blackjack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Classe principale du jeu Blackjack.
 */
public class Main extends Game {
    private Skin skin;
    private Music backgroundMusic;

    /**
     * Méthode appelée lors du lancement de l'application.
     * Initialise la musique de fond et le skin, puis affiche l'écran du menu.
     */
    @Override
    public void create() {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background_music.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        setScreen(new MenuScreen(this, this.skin));
    }

    /**
     * Affiche l'écran du casino.
     */
    public void showCasinoScreen() {
        setScreen(new CasinoScreen(this, this.skin));
    }

    /**
     * Affiche l'écran du jeu.
     */
    public void showGameScreen() {
        setScreen(new GameScreen(this, this.skin));
    }

    /**
     * Affiche l'écran des paramètres.
     */
    public void SettingsScreen() {
        setScreen(new SettingsScreen(this, this.skin));
    }

    /**
     * Retourne la musique de fond en cours de lecture.
     *
     * @return la musique de fond
     */
    public Music getBackgroundMusic() {
        return backgroundMusic;
    }
}