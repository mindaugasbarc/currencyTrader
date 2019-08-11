package com.learning.spring.currencies.model;

public class Money {

    private final Currency currency;
    private final double amount;

    public Money(Currency currency, double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("money should never be negative");
        }


        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }
}
