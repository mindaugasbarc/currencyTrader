package com.learning.spring.currencies.request;

import java.util.Objects;

public class SendMoneyRequest {

    private String toUser;
    private String currencyName;
    private double amount;

    public SendMoneyRequest() {
    }

    public SendMoneyRequest(String toUser, String currencyName, double amount) {
        this.toUser = toUser;
        this.currencyName = currencyName;
        this.amount = amount;
    }

    public String getToUser() {
        return toUser;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendMoneyRequest that = (SendMoneyRequest) o;
        return Double.compare(that.amount, amount) == 0 &&
                Objects.equals(toUser, that.toUser) &&
                Objects.equals(currencyName, that.currencyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toUser, currencyName, amount);
    }

    @Override
    public String toString() {
        return "SendMoneyRequest{" +
                "toUser='" + toUser + '\'' +
                ", currencyName='" + currencyName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
