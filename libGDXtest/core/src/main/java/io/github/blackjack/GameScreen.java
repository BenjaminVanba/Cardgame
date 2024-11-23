package io.github.blackjack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;

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

    private Dealer dealer;
    private HumanPlayer player;

    private Label deckCountLabel; // Label pour afficher le nombre de cartes

    private TextButton cheatButton; // Bouton pour tricher
    private Label cheatLabel; // Affiche la prochaine carte visible

    public GameScreen(Main main, Skin skin, String casinoType) {
        this.main = main;
        this.skin = skin;
        this.stage = new Stage(new FitViewport(1960, 1080));
        Gdx.input.setInputProcessor(stage);
        this.batch = new SpriteBatch();
        this.casinoType = casinoType;
        // Initialisez les joueurs une seule fois
        if (dealer == null && player == null) {
            dealer = new Dealer("Croupier");
            player = new HumanPlayer("Joueur", 1000);
        }

        // Instanciez GameLogic avec les joueurs existants
        gameLogic = new GameLogic(stage, dealer, player);

        deckCountLabel = new Label("Cartes restantes: " + gameLogic.deck.getRemainingCards(), skin);
        deckCountLabel.setPosition(1920 / 2 + 700, 1080 / 2 - 230); // Juste en dessous de l'affichage du deck
        stage.addActor(deckCountLabel);

        // Réinitialiser les positions et mains des joueurs
        // gameLogic.player.resetPositionAndHand();
        System.out.println(gameLogic.player.playerId);
        player.setPositionBasedOnType(false);
        // gameLogic.dealer.resetPositionAndHand();
        System.out.println(gameLogic.dealer.playerId);
        dealer.setPositionBasedOnType(true);
        // resetPositionOfPlayer(gameLogic.dealer);

    }

    @Override
    public void show() {
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
        betButton.setSize(300, 100);
        betButton.setPosition(20, 800);
        stage.addActor(betButton);

        hitButton = new TextButton("Distribuer", skin);
        hitButton.setSize(300, 100);
        hitButton.setPosition(20, 680);
        stage.addActor(hitButton);

        standButton = new TextButton("Rester", skin);
        standButton.setSize(300, 100);
        standButton.setPosition(20, 560);
        stage.addActor(standButton);

        restartButton = new TextButton("Relancer", skin);
        restartButton.setSize(300, 100);
        restartButton.setPosition(20, 440);
        restartButton.setVisible(false);
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

        // Bouton "Tricher"
        cheatButton = new TextButton("Tricher", skin);
        cheatButton.setSize(150, 50);
        cheatButton.setPosition(1920 / 2 + 700, 1080 / 2 - 330); // Positionné en bas à gauche
        stage.addActor(cheatButton);

        // Listener pour le bouton "Tricher"
        cheatButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    gameLogic.deck.toggleDeckVisibility();
                } catch (IllegalStateException e) {
                }
            }
        });

        // Positionner le score du croupier juste en dessous de son paquet en haut de
        // l'écran avec un léger déplacement vers le bas
        Pixmap dealerPixmap = new Pixmap(300, 80, Pixmap.Format.RGBA8888);

        // Remplir le pixmap avec un fond semi-transparent
        dealerPixmap.setColor(0, 0, 0, 0.6f); // Noir semi-transparent
        dealerPixmap.fill();

        // Dessiner la bordure
        dealerPixmap.setColor(Color.WHITE); // Couleur de la bordure
        dealerPixmap.drawRectangle(0, 0, dealerPixmap.getWidth(), dealerPixmap.getHeight());

        // Créer un Drawable à partir du Pixmap
        Drawable dealerBackground = new TextureRegionDrawable(new Texture(dealerPixmap));

        // Libérer le pixmap (la texture gère le reste)
        dealerPixmap.dispose();

        // Créer le label du score du croupier
        dealerScoreLabel = new Label("Score du croupier: 0", skin);
        dealerScoreLabel.getStyle().background = dealerBackground;
        dealerScoreLabel.setFontScale(1.5f);
        dealerScoreLabel.setSize(dealerBackground.getMinWidth(), dealerBackground.getMinHeight());
        dealerScoreLabel.setAlignment(Align.center);
        dealerScoreLabel.setPosition(Gdx.graphics.getWidth() / 2 - dealerScoreLabel.getWidth() / 2,
                Gdx.graphics.getHeight() - 410); // Ajusté la position
        stage.addActor(dealerScoreLabel);

        // Créer un Pixmap pour le fond du joueur avec bordure et transparence
        Pixmap playerPixmap = new Pixmap(300, 80, Pixmap.Format.RGBA8888);

        // Remplir le pixmap avec un fond semi-transparent
        playerPixmap.setColor(0, 0, 0, 0.6f); // Noir semi-transparent
        playerPixmap.fill();

        // Dessiner la bordure
        playerPixmap.setColor(Color.WHITE); // Couleur de la bordure
        playerPixmap.drawRectangle(0, 0, playerPixmap.getWidth(), playerPixmap.getHeight());

        // Créer un Drawable à partir du Pixmap
        Drawable playerBackground = new TextureRegionDrawable(new Texture(playerPixmap));

        // Libérer le pixmap
        playerPixmap.dispose();

        // Créer le label du score du joueur
        payerScorLabel = new Label("Score du joueur: 0", skin);
        payerScorLabel.getStyle().background = playerBackground;
        payerScorLabel.setFontScale(1.5f);
        payerScorLabel.setSize(playerBackground.getMinWidth(), playerBackground.getMinHeight());
        payerScorLabel.setAlignment(Align.center);
        payerScorLabel.setPosition(Gdx.graphics.getWidth() / 2 - payerScorLabel.getWidth() / 2,
                360); // Ajusté la position
        stage.addActor(payerScorLabel);

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

        // Mettre à jour le label du nombre de cartes restantes
        deckCountLabel.setText("Cartes restantes: " + gameLogic.deck.getRemainingCards());
        updateScores();

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
