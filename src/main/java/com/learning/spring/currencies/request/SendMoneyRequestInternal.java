package com.learning.spring.currencies.request;

import com.learning.spring.currencies.model.Money;
import com.learning.spring.user.model.User;

public final class SendMoneyRequestInternal {

    private final User userFrom;
    private final User userTo;
    private final  Money money;


    public SendMoneyRequestInternal(User userFrom, User userTo, Money money) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.money = money;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public Money getMoney() {
        return money;
    }
}
