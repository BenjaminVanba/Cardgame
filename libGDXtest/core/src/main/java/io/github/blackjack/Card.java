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
    protected String CardTexturePath;
    protected String hiddenCardTexturePath;
    protected String shownCardTexturePath;

    protected static final String[] SUITS = { "Coeurs", "Carreaux", "Trefles", "Piques" };
    protected static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    protected static final int[] VALUES = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };

    protected static final Map<String, Integer> rankValueMap = new HashMap<>();

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
        this.CardTexturePath = "Cards/" + this.suit + "_" + this.rank + "_white.png";
        this.hiddenCardTexturePath = "Cards/hidden_white.png";
    }


    public Card(boolean hidden) {
        Random random = new Random();
        this.rank = RANKS[random.nextInt(RANKS.length)];
        this.value = rankValueMap.get(this.rank);
        this.suit = SUITS[random.nextInt(SUITS.length)];
        this.hidden = hidden;
        this.CardTexturePath = "Cards/" + this.suit + "_" + this.rank + "_white.png";
        this.hiddenCardTexturePath = "Cards/hidden_white.png";
    }

    public Card(String rank, String suit, boolean hidden) {
        this.value = rankValueMap.get(rank);
        this.rank = rank;
        this.suit = suit;
        this.hidden = hidden;
        this.CardTexturePath = "Cards/" + this.suit + "_" + this.rank + "_white.png";
        this.hiddenCardTexturePath = "Cards/hidden_white.png";
    }

    public void DisplayInfo(Card card) {
        System.out.println("* " + card.getValue() + " de " + card.getSuit() + " *");
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

    public String getCardTexturePath() {
        return this.CardTexturePath;
    }

    public String gethiddenCardTexturePath() {
        return this.hiddenCardTexturePath;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public boolean isAce() {
        if (this.rank.equals("A")) {
            return true;
        } else {
            return false;
        }
    }
}