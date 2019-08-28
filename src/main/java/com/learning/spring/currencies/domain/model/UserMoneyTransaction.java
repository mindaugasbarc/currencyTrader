package com.learning.spring.currencies.domain.model;

import com.learning.spring.user.domain.model.User;

public final class UserMoneyTransaction {

    private final User userFrom;
    private final User userTo;
    private final  Money money;


    public UserMoneyTransaction(User userFrom, User userTo, Money money) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.money = money;
    }

    public void doTransaction() {
        getUserFrom().chargeMoney(getMoney());
        getUserTo().receiveMoney(getMoney());
    }

    private User getUserFrom()
    {
        return userFrom;
    }

    private User getUserTo() {
        return userTo;
    }

    private Money getMoney() {
        return money;
    }
}
