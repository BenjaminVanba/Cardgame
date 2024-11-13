package io.github.blackjack;

import java.util.Random;

public class Card implements ICard {
    protected String value;
    protected String type;

    private static final String[] SUITS = { "Cœurs", "Carreaux", "Trèfles", "Piques" };
    private static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

    public Card() {
        Random random = new Random();
        this.type = SUITS[random.nextInt(SUITS.length)];
        this.value = RANKS[random.nextInt(RANKS.length)];
    }

    public Card(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public void DisplayInfo(Card card) {
        System.out.println("* " + card.getValue() + " de " + card.getType() + " *");
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
