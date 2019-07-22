package com.learning.spring.currencies.request;

public class MoneyExchangeRequest {

    private String fromCurrencyName;
    private String toCurrencyName;
    private double amount;

    public MoneyExchangeRequest(String fromCurrencyName, String toCurrencyName, double amount) {
        this.fromCurrencyName = fromCurrencyName;
        this.toCurrencyName = toCurrencyName;
        this.amount = amount;
    }

    public String getFromCurrencyName() {
        return fromCurrencyName;
    }

    public String getToCurrencyName() {
        return toCurrencyName;
    }

    public double getAmount() {
        return amount;
    }
}
