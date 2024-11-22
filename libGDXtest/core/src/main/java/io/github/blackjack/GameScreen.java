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
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * La classe {@code GameScreen} représente l'écran de jeu principal dans le jeu
 * de Blackjack.
 * Elle implémente l'interface {@link Screen} de libGDX, fournissant les
 * méthodes nécessaires
 * pour gérer l'affichage et les interactions utilisateur sur cet écran.
 * 
 * <p>
 * Cette classe est responsable de l'affichage des cartes, de la gestion des
 * interactions
 * utilisateur telles que le retour au menu, et de la mise à jour de l'état de
 * l'écran de jeu.
 * </p>
 * 
 * @see Screen
 * @see Main
 * @see MenuScreen
 */
public class GameScreen implements Screen {
    /**
     * Référence à l'instance principale du jeu {@link Main}.
     */
    private Main main;

    /**
     * Stage de scène pour gérer les acteurs et les interactions utilisateur.
     */
    private Stage stage;

    private TextButton backButton, betButton, hitButton, standButton, restartButton;
    private GameLogic gameLogic;
    private Label resultLabel; // Pour afficher le message
    private Texture nextCardTexture;
    private String casinoType;
    private Skin skin;
    private Label payerScorLabel;
    private Label dealerScoreLabel;

    /**
     * Texture de la carte affichée à l'écran.
     */
    private Texture cardTexture;

    /**
     * Image représentant la carte affichée à l'écran.
     */
    // private Image cardImage;

    /**
     * Texture de l'arrière-plan de l'écran de jeu.
     */
    private Texture backgroundTexture;

    /**
     * SpriteBatch utilisé pour dessiner les sprites à l'écran.
     */
    private SpriteBatch batch;

    /**
     * Bouton permettant de revenir au menu principal.
     */
    // private TextButton backButton;

    /**
     * Construit une nouvelle instance de {@code GameScreen} avec les paramètres
     * spécifiés.
     *
     * @param main référence à l'instance principale du jeu {@link Main}
     * @param skin skin utilisé pour l'interface utilisateur
     */
    public GameScreen(Main main, Skin skin, String casinoType) {
        this.main = main;
        this.skin = skin;
        this.stage = new Stage(new FitViewport(1960, 1080));
        Gdx.input.setInputProcessor(stage);
        this.batch = new SpriteBatch();
        this.casinoType = casinoType;
    }

    @Override
    public void show() {
        gameLogic = new GameLogic(stage);
        stage.addActor(gameLogic);

        // Charger l'arrière-plan si ce n'est pas déjà fait
        if (casinoType.equals("casino1")) {
            backgroundTexture = new Texture(Gdx.files.internal("CasinoSuper.png"));
        } else if (casinoType.equals("casino2")) {
            backgroundTexture = new Texture(Gdx.files.internal("Background.jpg"));
        }

        backButton = new TextButton("Retour au menu", skin);

        backButton.setSize(150, 50);
        backButton.setPosition(1960 - backButton.getWidth() - 10, 1080 - backButton.getHeight() - 10);
        stage.addActor(backButton);

        backButton.addListener(new ClickListener() {
            /**
             * Méthode appelée lors d'un clic sur le bouton de retour au menu.
             *
             * @param event l'événement d'entrée
             * @param x     la coordonnée x du clic
             * @param y     la coordonnée y du clic
             */
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

        restartButton = new TextButton("Relancer", skin);
        restartButton.setSize(150, 50);
        restartButton.setPosition(10, 70); // Position différente des autres
        restartButton.setVisible(false); // Caché par défaut
        stage.addActor(restartButton);

        resultLabel = new Label("", skin);
        resultLabel.setPosition(1960 / 2 - 100, 1080 / 2);
        stage.addActor(resultLabel);

        gameLogic.resetGame();
        updateButtonVisibility(); // Met à jour l'affichage des boutons

        betButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (gameLogic.isGameFinished()) { // Vérifie si la partie est terminée
                    resultLabel.setText(""); // Réinitialise l'affichage des résultats
                }
                if (gameLogic.isWaitingForBet()) {
                    gameLogic.distributeInitialCards(); // Distribue les cartes
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            updateScores();
                        }
                    }, 1.6f);
                    updateButtonVisibility(); // Met à jour l'affichage des boutons
                }

            }
        });

        hitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameLogic.playerHits();
                updateScores();
                resultLabel.setText(gameLogic.getResultMessage());
                updateButtonVisibility(); // Met à jour l'affichage des boutons
            }
        });

        standButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameLogic.setGameFinished(true);
                gameLogic.setWaitingForBet(false);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        updateScores();
                    }
                }, 0.2f);
                gameLogic.playerStands();
                resultLabel.setText(gameLogic.getResultMessage());
                updateButtonVisibility(); // Met à jour l'affichage des boutons
            }
        });

        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameLogic.resetGame(); // Réinitialise la logique du jeu

                updateScores();
                resultLabel.setText(""); // Efface le résultat précédent
                updateButtonVisibility(); // Met à jour l'affichage des boutons
            }
        });

        payerScorLabel = new Label("Score du joueur: 0", skin);
        payerScorLabel.setPosition(10, 150);
        stage.addActor(payerScorLabel);

        dealerScoreLabel = new Label("Score du croupier: 0", skin);
        dealerScoreLabel.setPosition(10, 180);
        stage.addActor(dealerScoreLabel);

    }

    private void updateScores() {
        int playerScore = gameLogic.getPlayerScore();
        int dealerScore = gameLogic.getDealerScore();

        payerScorLabel.setText("Score du joueur: " + playerScore);
        dealerScoreLabel.setText("Score du croupier: " + dealerScore);
    }

    /**
     * Méthode appelée une fois par frame pour dessiner l'écran.
     *
     * @param delta le temps écoulé depuis la dernière frame en secondes
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Mettre à jour la texture de la prochaine carte
        if (gameLogic.deck.getRemainingCards() > 0) {
            Card nextCard = gameLogic.deck.peekNextCard(); // Obtenir la prochaine carte
            if (nextCardTexture != null) {
                nextCardTexture.dispose(); // Libérer la précédente texture
            }
            if (nextCard.hidden) {
                nextCardTexture = new Texture(Gdx.files.internal(nextCard.gethiddenCardTexturePath())); // Charger la
                                                                                                        // texture
            } else {
                nextCardTexture = new Texture(Gdx.files.internal(nextCard.getCardTexturePath())); // Charger la texture
            }
        }

        batch.begin();
        // Dessiner l'arrière-plan
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Afficher la prochaine carte visible au milieu de l'écran légèrement à droite
        if (nextCardTexture != null) {
            float cardWidth = 200; // Largeur de la carte
            float cardHeight = 300; // Hauteur de la carte
            float cardX = (Gdx.graphics.getWidth() - 300); // Centre de l'écran + décalage à droite
            float cardY = (Gdx.graphics.getHeight() / 2) - (cardHeight / 2); // Centré verticalement
            batch.draw(nextCardTexture, cardX, cardY, cardWidth, cardHeight);
        }
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    /**
     * Méthode appelée lorsqu'il y a un changement de taille de l'écran.
     *
     * @param width  la nouvelle largeur de l'écran
     * @param height la nouvelle hauteur de l'écran
     */

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'resize'");
        stage.getViewport().update(width, height, true);
    }

    /**
     * Méthode appelée lorsque le jeu est mis en pause.
     */
    @Override
    public void pause() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'pause'");
        // Implémentation de la méthode pause() si nécessaire
    }

    /**
     * Méthode appelée lorsque le jeu est repris après une pause.
     */
    @Override
    public void resume() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'resume'");
        // Implémentation de la méthode resume() si nécessaire
    }

    /**
     * Méthode appelée lorsque l'écran n'est plus le rendu courant pour le jeu.
     */
    @Override
    public void hide() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'hide'");
        // Implémentation de la méthode hide() si nécessaire
    }

    /**
     * Libère les ressources utilisées par l'écran.
     */
    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }

    public void updateButtonVisibility() {
        // Vérifie les états dans GameLogic pour déterminer quels boutons afficher
        boolean gameFinished = gameLogic.isGameFinished();
        boolean waitingForBet = gameLogic.isWaitingForBet();

        System.out.println("Mise a jour des boutnon: ");
        System.out.println("waitingForBet:  " + waitingForBet);
        System.out.println("gameFinished:  " + gameFinished);

        // Début de partie, afficher uniquement "Miser"
        if (gameFinished) {
            betButton.setVisible(false);
            hitButton.setVisible(false);
            standButton.setVisible(false);
            restartButton.setVisible(true);
        }

        else if (waitingForBet) {
            betButton.setVisible(true);
            hitButton.setVisible(false);
            standButton.setVisible(false);
            restartButton.setVisible(false);
        } else if (!gameFinished) {
            betButton.setVisible(false);
            hitButton.setVisible(true);
            standButton.setVisible(true);
            restartButton.setVisible(false);
        }
    }
}
