package io.github.blackjack;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;

public class GameLogic extends Actor {
    Stage stage;
    Deck deck;
    HumanPlayer player;
    Dealer dealer;
    private String resultMessage = ""; // Message pour afficher le gagnant
    private boolean gameFinished = true; // Indique si la partie est terminée
    private boolean waitingForBet = true;

    public GameLogic(Stage stage, Dealer dealer, HumanPlayer player) {
        this.stage = stage;
        this.dealer = dealer;
        this.player = player;

        // Ajoutez les joueurs existants au stage
        stage.addActor(this.dealer);
        stage.addActor(this.player);

        this.deck = new Deck(6); // Initialisation du deck
        resetGame(); // Réinitialise l'état du jeu
    }

    public int getPlayerScore() {
        return player.hand.getScore();
    }

    public int getDealerScore() {
        return dealer.hand.getVisibleScore();
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void setWaitingForBet(boolean waitingForBet) {
        this.waitingForBet = waitingForBet;
    }

    public void distributeInitialCards() {
        if (!waitingForBet)
            return; // Ne redistribue pas si le jeu n'attend pas une mise

        player.hand.clear();
        dealer.hand.clear();

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                drawFromDeck(player, false);
            }
        }, 0);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                drawFromDeck(dealer, true);
            }
        }, 0.5f);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                drawFromDeck(player, false);
            }
        }, 1f);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                drawFromDeck(dealer, false);
            }
        }, 1.5f);

        waitingForBet = false; // Le jeu commence
    }

    public void playerHits() {
        drawFromDeck(player, false);

        // Si le joueur se brûle, le jeu est terminé
        if (player.hand.isBurnt()) {
            resultMessage = "Vous êtes brûlé !";
            gameFinished = true;
            waitingForBet = true;
        }
    }

    public void playerStands() {
        dealer.hand.unhideCard(); // Montre les cartes cachées du croupier

        while (true) {
            int playerScore = player.hand.getScore();
            int dealerScore = dealer.hand.getScore();

            // Vérifie si le croupier doit s'arrêter ou continue de tirer
            if (dealerScore >= 17 || dealerScore > playerScore || dealer.hand.isBurnt()) {
                break;
            }

            // Le croupier tire une carte
            drawFromDeck(dealer, false);
        }

        // Détermine le gagnant
        determineWinner();
        gameFinished = true; // Marque la partie comme terminée
    }

    private void drawFromDeck(Player player, boolean isHidden) {
        player.hand.addCard(deck.drawCard(), isHidden);
    }

    private int getValidScore(Hand hand) {
        return hand.getMaxValue() <= 21 ? hand.getMaxValue() : hand.getMinValue();
    }

    private void determineWinner() {
        int playerScore = getValidScore(player.hand);
        int dealerScore = getValidScore(dealer.hand);

        if (dealer.hand.isBurnt() || playerScore > dealerScore) {
            if (dealer.hand.isBurnt()) {
                resultMessage = "Vous avez gagné ! Le croupier s'est brûlé !";
                this.gameFinished = true;
                this.waitingForBet = true;
            } else {
                resultMessage = "Vous avez gagné !";
                this.gameFinished = true;
                this.waitingForBet = true;
            }
        } else if (playerScore == dealerScore) {
            resultMessage = "Égalité !";
            this.gameFinished = true;
            this.waitingForBet = true;
        } else {
            resultMessage = "Le croupier a gagné !";
            this.gameFinished = true;
            this.waitingForBet = true;
        }
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public boolean isWaitingForBet() {
        return waitingForBet;
    }

    public void resetGame() {
        player.hand.resetHand(); // Vide la main du joueur
        dealer.hand.resetHand(); // Vide la main du croupier
        player.setPositionBasedOnType(false); // Réinitialise la position et la main du joueur
        dealer.setPositionBasedOnType(true); // Réinitialise la position et la main du croupier
        // deck = new Deck(); // Recharge le deck
        resultMessage = ""; // Réinitialise le message de résultat
        gameFinished = false; // Le jeu n'est plus terminé
        waitingForBet = true; // Attend que le joueur clique sur "Miser"
    }
}
