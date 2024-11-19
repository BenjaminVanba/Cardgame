
// package io.github.blackjack;

// import com.badlogic.gdx.ApplicationAdapter;
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.audio.Music;
// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.math.MathUtils;
// import com.badlogic.gdx.scenes.scene2d.Actor;
// import com.badlogic.gdx.scenes.scene2d.Stage;
// import com.badlogic.gdx.scenes.scene2d.actions.Actions;
// import com.badlogic.gdx.scenes.scene2d.ui.Skin;
// import com.badlogic.gdx.scenes.scene2d.ui.Slider;
// import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
// import com.badlogic.gdx.scenes.scene2d.ui.Window;
// import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
// import com.badlogic.gdx.utils.ScreenUtils;
// import com.badlogic.gdx.utils.viewport.FitViewport;

// public class Main extends ApplicationAdapter {
// private Stage stage;
// private Skin skin;
// private Texture backgroundTexture;
// private SpriteBatch batch;
// private float backgroundWidth, backgroundHeight;
// private Music music;
// private Window menuWindow, optionsWindow;
// private Slider volumeSlider;

// @Override
// public void create() {
// batch = new SpriteBatch();
// backgroundTexture = new Texture(Gdx.files.internal("Background.jpg"));

// backgroundWidth = backgroundTexture.getWidth();
// backgroundHeight = backgroundTexture.getHeight();

// stage = new Stage(new FitViewport(1024, 768));
// skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

// // Charger la musique
// music = Gdx.audio.newMusic(Gdx.files.internal("background_music.mp3"));
// music.setLooping(true); // La musique se répète en boucle
// music.play(); // Joue la musique au démarrage

// // Créer le menu
// createMainMenu();

// // Ajout du menu à la scène
// stage.addActor(menuWindow);

// Gdx.input.setInputProcessor(stage);
// }

// private void createMainMenu() {
// menuWindow = new Window("Menu", skin, "border");
// menuWindow.defaults().pad(10f);
// menuWindow.add("Bienvenue dans le jeu!").row();

// // Bouton "Commencer la partie"
// TextButton startButton = new TextButton("Commencer la partie", skin);
// startButton.pad(8f);
// startButton.addListener(new ChangeListener() {
// @Override
// public void changed(ChangeEvent event, Actor actor) {
// startGame();
// }
// });
// menuWindow.add(startButton).row();

// // Bouton "Options"
// TextButton optionsButton = new TextButton("Options", skin);
// optionsButton.pad(8f);
// optionsButton.addListener(new ChangeListener() {
// @Override
// public void changed(ChangeEvent event, Actor actor) {
// showOptions();
// }
// });
// menuWindow.add(optionsButton).row();
// // Bouton "Quitter"
// TextButton quitButton = new TextButton("Quitter", skin);
// quitButton.pad(8f);
// quitButton.addListener(new ChangeListener() {
// @Override
// public void changed(ChangeEvent event, Actor actor) {
// quitGame();
// }
// });
// menuWindow.add(quitButton).row();

// // Centrer le menu
// menuWindow.pack();
// menuWindow.setPosition(MathUtils.roundPositive(stage.getWidth() / 2f -
// menuWindow.getWidth() / 2f),
// MathUtils.roundPositive(stage.getHeight() / 2f - menuWindow.getHeight() /
// 2f));
// }

// private void startGame() {
// // Logique pour démarrer la partie
// System.out.println("Commencer la partie...");
// }

// private void quitGame() {
// // Ferme l'application
// Gdx.app.exit();
// }

// private void showOptions() {
// // Créer une fenêtre d'options
// optionsWindow = new Window("Options", skin, "border");
// optionsWindow.defaults().pad(10f);

// // Créer un slider pour ajuster le volume de la musique
// volumeSlider = new Slider(0f, 1f, 0.01f, false, skin);
// volumeSlider.setValue(music.getVolume()); // Définir la valeur initiale du
// slider à la valeur actuelle du volume
// volumeSlider.addListener(new ChangeListener() {
// @Override
// public void changed(ChangeEvent event, Actor actor) {
// float volume = volumeSlider.getValue();
// music.setVolume(volume); // Met à jour le volume de la musique
// }
// });

// // Ajouter le slider à la fenêtre d'options
// optionsWindow.add("Volume de la musique").row();
// optionsWindow.add(volumeSlider).row();

// // Bouton de retour au menu principal
// TextButton backButton = new TextButton("Retour", skin);
// backButton.pad(8f);
// backButton.addListener(new ChangeListener() {
// @Override
// public void changed(ChangeEvent event, Actor actor) {
// hideOptions();
// }
// });
// optionsWindow.add(backButton).row();

// // Centrer la fenêtre d'options
// optionsWindow.pack();
// optionsWindow.setPosition(MathUtils.roundPositive(stage.getWidth() / 2f -
// optionsWindow.getWidth() / 2f),
// MathUtils.roundPositive(stage.getHeight() / 2f - optionsWindow.getHeight() /
// 2f));

// // Masquer le menu principal et afficher la fenêtre d'options
// menuWindow.setVisible(false);
// stage.addActor(optionsWindow);
// }

// private void hideOptions() {
// // Masquer la fenêtre d'options et afficher le menu principal
// optionsWindow.setVisible(false);
// menuWindow.setVisible(true);
// }

// @Override
// public void render() {
// ScreenUtils.clear(0f, 0f, 0f, 1f);

// batch.begin();

// // Calculer l'échelle pour garder les proportions
// float scale = Math.max(Gdx.graphics.getWidth() / backgroundWidth,
// Gdx.graphics.getHeight() / backgroundHeight);

// // Dessiner l'arrière-plan en fonction de l'échelle calculée
// float scaledWidth = backgroundWidth * scale;
// float scaledHeight = backgroundHeight * scale;
// float xOffset = (Gdx.graphics.getWidth() - scaledWidth) / 2f;
// float yOffset = (Gdx.graphics.getHeight() - scaledHeight) / 2f;

// batch.draw(backgroundTexture, xOffset, yOffset, scaledWidth, scaledHeight);
// batch.end();

// // Dessiner la scène
// stage.act(Gdx.graphics.getDeltaTime());
// stage.draw();
// }

// @Override
// public void resize(int width, int height) {
// stage.getViewport().update(width, height);
// }

// @Override
// public void dispose() {
// stage.dispose();
// skin.dispose();
// batch.dispose();
// backgroundTexture.dispose();
// music.dispose(); // Libérer la musique
// }
// }