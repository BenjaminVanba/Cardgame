package io.github.blackjack;

import java.util.Random;

public class Card {
    protected int value;
    protected String rank;
    protected String suit;

    private static final String[] SUITS = { "Cœurs", "Carreaux", "Trèfles", "Piques" };
    private static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    private static final int[] VALUES = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };

    public Card() {
        Random random = new Random();
        this.value = VALUES[random.nextInt(VALUES.length)];
        this.rank = RANKS[random.nextInt(RANKS.length)];
        this.suit = SUITS[random.nextInt(SUITS.length)];
    }

    public Card(int value, String rank, String suit) {
        this.value = value;
        this.rank = rank;
        this.suit = suit;
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
        if (this.value == 14) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isHead() {
        if (this.value >= 10 && this.value != 14) {
            return true;
        } else {
            return false;
        }
    }
}
