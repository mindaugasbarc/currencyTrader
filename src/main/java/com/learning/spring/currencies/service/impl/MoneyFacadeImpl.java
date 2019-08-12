package com.learning.spring.currencies.service.impl;

import com.learning.spring.currencies.exception.CurrencyNotFoundException;
import com.learning.spring.currencies.model.Currency;
import com.learning.spring.currencies.model.Money;
import com.learning.spring.currencies.repository.CurrencyRepository;
import com.learning.spring.currencies.request.MoneyExchangeRequest;
import com.learning.spring.currencies.request.SendMoneyRequest;
import com.learning.spring.currencies.request.SendMoneyRequestInternal;
import com.learning.spring.currencies.service.ExchangeService;
import com.learning.spring.currencies.service.MoneyFacade;
import com.learning.spring.currencies.service.MoneyTransactionService;
import com.learning.spring.user.UserRepository;
import com.learning.spring.user.exception.UserNotFoundException;
import com.learning.spring.user.model.User;
import com.learning.spring.user.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MoneyFacadeImpl implements MoneyFacade {

    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;
    private final ExchangeService exchangeService;
    private final UserAuthenticationService userAuthenticationService;
    private final MoneyTransactionService moneyTransactionService;

    @Autowired
    public MoneyFacadeImpl(CurrencyRepository currencyRepository, UserRepository userRepository, ExchangeService exchangeService, UserAuthenticationService userAuthenticationService, MoneyTransactionService moneyTransactionService) {
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
        this.exchangeService = exchangeService;
        this.userAuthenticationService = userAuthenticationService;
        this.moneyTransactionService = moneyTransactionService;
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
    public void send(SendMoneyRequest request, String authToken) {
        User userTo = userRepository.findByUserDetails_Username(request.getToUser())
                .orElseThrow(() -> new UserNotFoundException(request.getToUser()));

        User userFrom = Optional.ofNullable(userAuthenticationService.findByToken(authToken))
                .orElseThrow(UserNotFoundException::new);

        Currency currency = currencyRepository.findById(request.getCurrencyName())
                .orElseThrow(() -> new CurrencyNotFoundException(request.getCurrencyName()));

        moneyTransactionService.doTransaction(new SendMoneyRequestInternal(userFrom, userTo,
                new Money(currency, request.getAmount())));

    }
}
