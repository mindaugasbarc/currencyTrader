package com.mfinancials.tradingengine.money.domain.model;

import com.mfinancials.tradingengine.money.domain.exception.CurrencyNotFoundException;
import com.mfinancials.tradingengine.money.domain.exception.InsufficientBalanceException;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Entity
public class Balance {

    @Id
    @GeneratedValue
    private long id;

    @ElementCollection
    @MapKeyClass(Currency.class)
    private Map<Currency, Double> currencies = new HashMap<>();

    @Transactional
    public void exchange(Money money, Currency to) {
        validateIsEnoughMoneyInBalance(money);
        double ratioBetweenCurrencies = money.getCurrency().getRatio().divide(to.getRatio(), RoundingMode.DOWN).doubleValue();
        addMoney(new Money(to, (double) Math.round(money.getAmount() * ratioBetweenCurrencies * 100) / 100));
        chargeMoney(money);
    }

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

    private void validateIsEnoughMoneyInBalance(Money money) {
        Double moneyInBalance = currencies.get(money.getCurrency());
        if (moneyInBalance == null) {
            throw new CurrencyNotFoundException(money.getCurrency().getName());
        } else if (moneyInBalance < money.getAmount()) {
            throw new InsufficientBalanceException();
        }
    }
}
