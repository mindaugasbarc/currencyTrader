package com.learning.spring.currencies.request;

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
}
