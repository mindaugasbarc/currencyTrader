package com.learning.spring.currencies.request;

import com.learning.spring.currencies.model.Money;

public final class SendMoneyRequest {

    private final String toUser;
    private final Money money;

    public SendMoneyRequest(String toUser, Money money) {
        this.toUser = toUser;
        this.money = money;
    }

    public String getToUser() {
        return toUser;
    }

    public Money getMoney() {
        return money;
    }
}
