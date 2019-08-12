package com.learning.spring.currencies.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Entity
public class Balance {

    @Id
    @GeneratedValue
    private long id;

    @ElementCollection
    @MapKeyClass(Currency.class)
    private Map<Currency, Double> currencies = new HashMap<>();

    public List<Money> getAllMoney() {
        return currencies.entrySet().stream()
                .map(entry -> new Money(entry.getKey(), entry.getValue()))
                .collect(toList());
    }

    public Money getMoney(Currency currency) {
        return currencies.containsKey(currency) ? new Money(currency, currencies.get(currency)) : new Money(currency, 0);
    }

    public void addMoney(Money money) {
        if (currencies.containsKey(money.getCurrency())) {
            currencies.put(money.getCurrency(), currencies.get(money.getCurrency()) + money.getAmount());
        } else {
            currencies.put(money.getCurrency(), money.getAmount());
        }
    }

    public void chargeMoney(Money money) {
        if (currencies.containsKey(money.getCurrency())) {
            currencies.put(money.getCurrency(), currencies.get(money.getCurrency()) - money.getAmount());
        } else {
         currencies.put(money.getCurrency(), money.getAmount() * -1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return Objects.equals(currencies, balance.currencies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencies);
    }

    @Override
    public String toString() {
        return "Balance{" +
                "currencies=" + currencies +
                '}';
    }
}
