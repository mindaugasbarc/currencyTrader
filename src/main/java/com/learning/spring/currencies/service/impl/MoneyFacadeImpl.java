package com.learning.spring.currencies.service.impl;

import com.learning.spring.currencies.exception.CurrencyNotFoundException;
import com.learning.spring.currencies.model.Currency;
import com.learning.spring.currencies.model.Money;
import com.learning.spring.currencies.repository.CurrencyRepository;
import com.learning.spring.currencies.request.MoneyExchangeRequest;
import com.learning.spring.currencies.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MoneyFacadeImpl implements MoneyFacade {

    private final CurrencyRepository currencyRepository;
    private final ExchangeService exchangeService;

    @Autowired
    public MoneyFacadeImpl(CurrencyRepository currencyRepository, ExchangeService exchangeService) {
        this.currencyRepository = currencyRepository;
        this.exchangeService = exchangeService;
    }

    @Override
    public Money exchange(MoneyExchangeRequest moneyExchangeRequest) {
        Currency from = currencyRepository.findById(moneyExchangeRequest.getFromCurrencyName())
                .orElseThrow(() -> new CurrencyNotFoundException(moneyExchangeRequest.getFromCurrencyName()));

        Currency to = currencyRepository.findById(moneyExchangeRequest.getToCurrencyName())
                .orElseThrow(() -> new CurrencyNotFoundException(moneyExchangeRequest.getToCurrencyName()));




        return exchangeService.exchange(new Money(from, moneyExchangeRequest.getAmount()), to);
    }
}
