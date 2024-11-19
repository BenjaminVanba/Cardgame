package io.github.blackjack;

public abstract class Player {
    protected Hand hand;
    protected double moneyAmount;
    protected String name;

    public Player(String name, double moneyAmount) {
        this.hand = new Hand();
        this.moneyAmount = moneyAmount;
        this.name = name;
    }

    public double getMoney() {
        return this.moneyAmount;
    }

    public String getName() {
        return this.name;
    }
}
