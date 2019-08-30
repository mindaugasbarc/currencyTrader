package com.mfinancials.tradingengine.money.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Entity
public class Balance {

    @Id
    @GeneratedValue
    private long id;

    private Map<Currency, Double> currencies = new HashMap<>();

    public Set<Money> getAllMoney() {
        return currencies.entrySet().stream()
                .map(entry -> new Money(entry.getKey(), entry.getValue()))
                .collect(toSet());
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
