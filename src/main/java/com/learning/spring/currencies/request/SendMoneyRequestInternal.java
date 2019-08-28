package com.learning.spring.currencies.request;

import com.learning.spring.currencies.model.Money;
import com.learning.spring.user.model.User;

import javax.transaction.Transactional;

public class SendMoneyRequestInternal {

    private final User userFrom;
    private final User userTo;
    private final  Money money;


    public SendMoneyRequestInternal(User userFrom, User userTo, Money money) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.money = money;
    }

    @Transactional
    public void doTransaction() {
        getUserFrom().chargeMoney(getMoney());
        getUserTo().receiveMoney(getMoney());
    }

    private User getUserFrom() {
        return userFrom;
    }

    private User getUserTo() {
        return userTo;
    }

    private Money getMoney() {
        return money;
    }
}
