package io.github.blackjack;

/**
 * La classe {@code AIPlayer} représente un joueur contrôlé par l'intelligence
 * artificielle dans le jeu de Blackjack.
 * Elle étend la classe {@link Player} en initialisant un joueur avec un nom
 * spécifique et un score de départ.
 * 
 * <p>
 * Cette classe permet de créer des instances de joueurs automatisés qui peuvent
 * participer au jeu sans intervention humaine.
 * </p>
 * 
 * @see Player
 */
public class AIPlayer extends Player {
    public AIPlayer(String name) {
        super(name, 0);
        // Card card = new Card("Q", "Coeurs", false);
        // Card card2 = new Card("K", "Piques", false);
        // hand.addCard(card);
        // hand.addCard(card2);
    }
}
