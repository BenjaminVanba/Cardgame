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

    /**
     * Construit un nouvel {@code AIPlayer} avec le nom par défaut "AIPlayer" et un
     * score initial de 0.
     */
    public AIPlayer() {
        super("AIPlayer", 0);
    }

    /**
     * Construit un nouvel {@code AIPlayer} avec le nom spécifié et un score initial
     * de 0.
     *
     * @param name le nom du joueur AI
     */
    public AIPlayer(String name) {
        super(name, 0);
    }
}
