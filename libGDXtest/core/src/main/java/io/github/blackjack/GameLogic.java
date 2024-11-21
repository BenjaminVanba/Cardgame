package io.github.blackjack;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;

public class GameLogic extends Actor {
    Stage stage;
    Deck deck;
    HumanPlayer player;
    AIPlayer dealer;
    private String resultMessage = ""; // Message pour afficher le gagnant

    public GameLogic(Stage stage) {
        this.stage = stage;
        this.deck = new Deck();
        this.player = new HumanPlayer("Toto", 1000);
        this.dealer = new AIPlayer("FrankLeCroupier");
        this.stage.addActor(player);
        this.stage.addActor(dealer);
    }

    public void distributeInitialCards() {
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
    }

    public void playerHits() {
        drawFromDeck(player, false);
        if (player.hand.isBurnt()) {
            resultMessage = "Vous êtes brûlé ! à " + player.hand.getMaxValue() + "/" + player.hand.getMinValue();
        }
    }

    public void playerStands() {
        dealer.hand.unhideCard(); // Montre les cartes cachées du croupier

        while (true) {
            int playerScore = getValidScore(player.hand);
            int dealerScore = getValidScore(dealer.hand);

            // Vérifie si le croupier doit s'arrêter ou continue de tirer
            if (dealerScore >= 17 || dealerScore > playerScore || dealer.hand.isBurnt()) {
                break;
            }

            // Le croupier tire une carte
            drawFromDeck(dealer, false);

            // Afficher la nouvelle carte du croupier
            stage.act(); // Met à jour les acteurs du stage pour refléter les changements
        }

        // Détermine le gagnant
        determineWinner();
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
            resultMessage = "Vous avez gagné !";
        } else if (playerScore == dealerScore) {
            resultMessage = "Égalité !";
        } else {
            resultMessage = "Le croupier a gagné !";
        }
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void resetGame() {
        // deck = new Deck(); // Réinitialise le deck
        player.hand.resetHand(); // Vide la main du joueur
        dealer.hand.resetHand(); // Vide la main du croupier
        resultMessage = ""; // Réinitialise le message
    }
}
