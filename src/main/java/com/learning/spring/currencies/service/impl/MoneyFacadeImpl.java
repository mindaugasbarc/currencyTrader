package com.learning.spring.currencies.service.impl;

import com.learning.spring.currencies.CurrencyRepository;
import com.learning.spring.currencies.exception.CurrencyNotFoundException;
import com.learning.spring.currencies.model.Currency;
import com.learning.spring.currencies.model.Money;
import com.learning.spring.currencies.request.MoneyExchangeRequest;
import com.learning.spring.currencies.service.ExchangeService;
import com.learning.spring.currencies.service.MoneyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MoneyFacadeImpl implements MoneyFacade {

    private final ExchangeService exchangeService;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public MoneyFacadeImpl(ExchangeService exchangeService, CurrencyRepository currencyRepository) {
        this.exchangeService = exchangeService;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Money exchange(MoneyExchangeRequest moneyExchangeRequest) {
        Currency fromCurrency = currencyRepository.findById(moneyExchangeRequest.getFromCurrencyName())
                .orElseThrow(() -> new CurrencyNotFoundException(moneyExchangeRequest.getFromCurrencyName()));

        Currency toCurrency = currencyRepository.findById(moneyExchangeRequest.getToCurrencyName())
                .orElseThrow(() -> new CurrencyNotFoundException(moneyExchangeRequest.getToCurrencyName()));

        return exchangeService.exchange(new Money(fromCurrency, moneyExchangeRequest.getAmount()), toCurrency);
    }
}
