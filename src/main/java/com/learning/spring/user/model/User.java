package com.learning.spring.user.model;

import com.learning.spring.currencies.model.Balance;
import com.learning.spring.currencies.model.Currency;
import com.learning.spring.currencies.model.Money;

import javax.persistence.*;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String username;

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private Balance balance;

    public User(String username, Balance balance) {
        this.username = username;
        this.balance = balance;
    }

    public User() {
    }

    public void exchange(Money money, Currency to) {
        balance.exchange(money, to);
    }

    public void chargeMoney(Money money) {
        balance.chargeMoney(money);
    }

    public void receiveMoney(Money money) {
        balance.addMoney(money);
    }

    public Map<String, Double> getBalance() {
        return balance.getAllMoney().stream()
                .collect(Collectors.toMap(entry -> entry.getCurrency().getName(), Money::getAmount));
    }
}
