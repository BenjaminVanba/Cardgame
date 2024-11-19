package io.github.blackjack;

public abstract class Casino {
    protected String name;
    protected double bank;
    protected String device;

    public String getName() {
        return this.name;
    }

    public double getBank() {
        return this.bank;
    }

    public String getDevice() {
        return this.device;
    }

    public Casino() {
        this.bank = 1000000.00;
        this.device = "EUR";
    }

    public Casino(double bankAmount) {
        this.bank = bankAmount;
        this.device = "EUR";
    }
}
