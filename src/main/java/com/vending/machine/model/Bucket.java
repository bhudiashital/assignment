package com.vending.machine.model;

public class Bucket<I, C> {
    private I item;
    private C coins;

    public Bucket(I item, C coins) {
        this.item = item;
        this.coins = coins;
    }

    public I getItem() {
        return item;
    }

    public C getCoins() {
        return coins;
    }
}
