package io.github.blackjack;

import java.util.HashMap;
import java.util.Random;
import java.util.Map;

/**
 * La classe {@code Card} représente une carte dans le jeu de Blackjack.
 * Chaque carte a une valeur, un rang, une couleur (suit) et peut être cachée ou
 * visible.
 * 
 * <p>
 * Cette classe permet de créer des instances de cartes avec des rangs et des
 * couleurs aléatoires.
 * </p>
 * 
 * @see Player
 */
public class Card {
    /**
     * La valeur numérique de la carte.
     */
    protected int value;

    /**
     * Le rang de la carte (par exemple, "2", "J", "A").
     */
    protected String rank;

    /**
     * La couleur de la carte (Cœurs, Carreaux, Trèfles, Piques).
     */
    protected String suit;

    /**
     * Indique si la carte est cachée (true) ou visible (false).
     */
    protected boolean hidden = true;

    /**
     * Tableau des couleurs disponibles pour les cartes.
     */
    private static final String[] SUITS = { "Cœurs", "Carreaux", "Trèfles", "Piques" };

    /**
     * Tableau des rangs disponibles pour les cartes.
     */
    private static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

    /**
     * Tableau des valeurs correspondantes aux rangs des cartes.
     */
    private static final int[] VALUES = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };

    /**
     * Carte de correspondance entre les rangs et leurs valeurs numériques.
     */
    private static final Map<String, Integer> rankValueMap = new HashMap<>();

    // Initialisation statique pour remplir la map des valeurs
    static {
        for (int i = 0; i < RANKS.length; i++) {
            rankValueMap.put(RANKS[i], VALUES[i]); // Associe chaque rang avec sa valeur
        }
    }

    /**
     * Construit une nouvelle {@code Card} avec un rang et une couleur aléatoires.
     * La valeur de la carte est déterminée en fonction de son rang.
     */
    public Card() {
        Random random = new Random();
        this.rank = RANKS[random.nextInt(RANKS.length)];
        this.value = rankValueMap.get(this.rank);
        this.suit = SUITS[random.nextInt(SUITS.length)];
    }

    /**
     * Retourne la valeur numérique de la carte.
     *
     * @return la valeur de la carte
     */
    public int getValue() {
        return value;
    }

    /**
     * Retourne le rang de la carte.
     *
     * @return le rang de la carte
     */
    public String getRank() {
        return rank;
    }

    /**
     * Retourne la couleur de la carte.
     *
     * @return la couleur de la carte
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Indique si la carte est cachée.
     *
     * @return {@code true} si la carte est cachée, {@code false} sinon
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Définit l'état de visibilité de la carte.
     *
     * @param hidden {@code true} pour cacher la carte, {@code false} pour la rendre
     *               visible
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * Retourne une représentation textuelle de la carte.
     * Si la carte est cachée, retourne "Carte cachée".
     * Sinon, retourne une chaîne décrivant le rang et la couleur de la carte.
     *
     * @return une chaîne représentant la carte
     */
    @Override
    public String toString() {
        if (hidden) {
            return "Carte cachée";
        }
        return rank + " de " + suit;
    }
}