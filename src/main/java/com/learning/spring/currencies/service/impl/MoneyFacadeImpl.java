package com.learning.spring.currencies.service.impl;

import com.learning.spring.currencies.exception.CurrencyNotFoundException;
import com.learning.spring.currencies.model.Currency;
import com.learning.spring.currencies.model.Money;
import com.learning.spring.currencies.repository.CurrencyRepository;
import com.learning.spring.currencies.request.MoneyExchangeRequest;
import com.learning.spring.currencies.request.SendMoneyRequest;
import com.learning.spring.currencies.service.ExchangeService;
import com.learning.spring.user.UserRepository;
import com.learning.spring.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MoneyFacadeImpl implements MoneyFacade {

    private final CurrencyRepository currencyRepository;
    private final ExchangeService exchangeService;
    private final UserRepository userRepository;

    @Autowired
    public MoneyFacadeImpl(CurrencyRepository currencyRepository, ExchangeService exchangeService, UserRepository userRepository) {
        this.currencyRepository = currencyRepository;
        this.exchangeService = exchangeService;
        this.userRepository = userRepository;
    }

    @Override
    public Money exchange(MoneyExchangeRequest moneyExchangeRequest) {
        Currency from = currencyRepository.findById(moneyExchangeRequest.getFromCurrencyName())
                .orElseThrow(() -> new CurrencyNotFoundException(moneyExchangeRequest.getFromCurrencyName()));

        Currency to = currencyRepository.findById(moneyExchangeRequest.getToCurrencyName())
                .orElseThrow(() -> new CurrencyNotFoundException(moneyExchangeRequest.getToCurrencyName()));




        return exchangeService.exchange(new Money(from, moneyExchangeRequest.getAmount()), to);
    }

    @Override
    public void send(SendMoneyRequest sendMoneyRequest) {
        Optional<User> user = userRepository.findByUserDetails_Username(sendMoneyRequest.getToUser());
        user.ifPresent(userr -> userr.getBalance().addMoney(sendMoneyRequest.getMoney()));
    }
}
