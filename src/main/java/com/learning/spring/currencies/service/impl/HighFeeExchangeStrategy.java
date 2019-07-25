package com.learning.spring.currencies.service.impl;

import com.learning.spring.currencies.model.Currency;
import com.learning.spring.currencies.model.Money;
import com.learning.spring.currencies.service.ExchangeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;

@Component
@Profile("fees")
public class HighFeeExchangeStrategy implements ExchangeService {

    @Override
    public Money exchange(Money money, Currency to) {
        double ratioBetweenCurrencies = money.getCurrency().getRatio().divide(to.getRatio(), RoundingMode.DOWN).doubleValue();
        return new Money(to, (double) (Math.round(money.getAmount() * ratioBetweenCurrencies *100) / 100) *0.9);
    }
}
