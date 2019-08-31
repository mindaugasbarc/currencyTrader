package com.mfinancials.tradingengine.money.application.request;

import java.util.Objects;

public class MoneyExchangeRequest {

    private final String fromCurrencyName;
    private final String toCurrencyName;
    private final double amount;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneyExchangeRequest that = (MoneyExchangeRequest) o;
        return Double.compare(that.amount, amount) == 0 &&
                Objects.equals(fromCurrencyName, that.fromCurrencyName) &&
                Objects.equals(toCurrencyName, that.toCurrencyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromCurrencyName, toCurrencyName, amount);
    }

    @Override
    public String toString() {
        return "MoneyExchangeRequest{" +
                "fromCurrencyName='" + fromCurrencyName + '\'' +
                ", toCurrencyName='" + toCurrencyName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
