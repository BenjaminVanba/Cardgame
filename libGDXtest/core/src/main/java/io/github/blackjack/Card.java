package io.github.blackjack;

import java.util.HashMap;
import java.util.Random;
import java.util.Map;

public class Card {
    protected int value;
    protected String rank;
    protected String suit;
    protected boolean hidden = true;

    protected static final String[] SUITS = { "Cœurs", "Carreaux", "Trèfles", "Piques" };
    protected static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    protected static final int[] VALUES = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };

    protected static final Map<String, Integer> rankValueMap = new HashMap<>();

    // Initialisation statique pour remplir la map des valeurs
    static {
        for (int i = 0; i < RANKS.length; i++) {
            rankValueMap.put(RANKS[i], VALUES[i]); // Associe chaque rang avec sa valeur
        }
    }

    public Card() {
        Random random = new Random();
        this.rank = RANKS[random.nextInt(RANKS.length)];
        this.value = rankValueMap.get(this.rank);
        this.suit = SUITS[random.nextInt(SUITS.length)];
    }

    public Card(boolean hidden) {
        Random random = new Random();
        this.rank = RANKS[random.nextInt(RANKS.length)];
        this.value = rankValueMap.get(this.rank);
        this.suit = SUITS[random.nextInt(SUITS.length)];
        this.hidden = hidden;
    }

    public Card(String rank, String suit, boolean hidden) {
        this.value = rankValueMap.get(rank);
        this.rank = rank;
        this.suit = suit;
        this.hidden = hidden;
    }

    public void DisplayInfo(Card card) {
        System.out.println("* " + card.getValue() + " de " + card.getSuit() + " *");
    }

    public String getRank() {
        return this.rank;
    }

    public int getValue() {
        return this.value;
    }

    public String getSuit() {
        return this.suit;
    }

    public boolean isAce() {
        if (this.rank.equals("A")) {
            return true;
        } else {
            return false;
        }

    }
}
