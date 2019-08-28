package com.learning.spring.currencies.service.impl;

import com.learning.spring.currencies.exception.CurrencyNotFoundException;
import com.learning.spring.currencies.model.Currency;
import com.learning.spring.currencies.model.Money;
import com.learning.spring.currencies.repository.CurrencyRepository;
import com.learning.spring.currencies.request.MoneyExchangeRequest;
import com.learning.spring.currencies.request.SendMoneyRequest;
import com.learning.spring.currencies.request.SendMoneyRequestInternal;
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
public final class MoneyFacadeImpl implements MoneyFacade {

    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;
    private final MoneyTransactionService moneyTransactionService;
    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public MoneyFacadeImpl(CurrencyRepository currencyRepository, UserRepository userRepository, MoneyTransactionService moneyTransactionService, UserAuthenticationService userAuthenticationService) {
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
        this.moneyTransactionService = moneyTransactionService;
        this.userAuthenticationService = userAuthenticationService;
    }

    @Override
    public void exchange(final MoneyExchangeRequest moneyExchangeRequest, final String token) {
        Currency from = currencyRepository.findById(moneyExchangeRequest.getFromCurrencyName())
                .orElseThrow(() -> new CurrencyNotFoundException(moneyExchangeRequest.getFromCurrencyName()));

        Currency to = currencyRepository.findById(moneyExchangeRequest.getToCurrencyName())
                .orElseThrow(() -> new CurrencyNotFoundException(moneyExchangeRequest.getToCurrencyName()));

        User user = userRepository.findByUsername(userAuthenticationService.validateTokenAndGetUsername(token)).orElseThrow(UserNotFoundException::new);
        user.exchange(new Money(from, moneyExchangeRequest.getAmount()), to);
    }

    @Override
    public void send(final SendMoneyRequest request, final String authToken) {
        User userTo = userRepository.findByUsername(request.getToUser())
                .orElseThrow(() -> new UserNotFoundException(request.getToUser()));

        User userFrom = userRepository
                .findByUsername(userAuthenticationService.validateTokenAndGetUsername(authToken))
                .orElseThrow(UserNotFoundException::new);

        Currency currency = currencyRepository.findById(request.getCurrencyName())
                .orElseThrow(() -> new CurrencyNotFoundException(request.getCurrencyName()));

        moneyTransactionService.doTransaction(new SendMoneyRequestInternal(userFrom, userTo,
                new Money(currency, request.getAmount())));
    }
}
