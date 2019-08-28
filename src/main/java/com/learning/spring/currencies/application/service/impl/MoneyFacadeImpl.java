package com.learning.spring.currencies.application.service.impl;

import com.learning.spring.currencies.domain.exception.CurrencyNotFoundException;
import com.learning.spring.currencies.domain.model.Currency;
import com.learning.spring.currencies.domain.model.Money;
import com.learning.spring.currencies.domain.repository.CurrencyRepository;
import com.learning.spring.currencies.application.request.MoneyExchangeRequest;
import com.learning.spring.currencies.application.request.SendMoneyRequest;
import com.learning.spring.currencies.domain.model.UserMoneyTransaction;
import com.learning.spring.currencies.application.service.MoneyFacade;
import com.learning.spring.user.domain.UserRepository;
import com.learning.spring.user.domain.exception.UserNotFoundException;
import com.learning.spring.user.domain.model.User;
import com.learning.spring.user.domain.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Map;

@Component
public class MoneyFacadeImpl implements MoneyFacade {

    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;
    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public MoneyFacadeImpl(CurrencyRepository currencyRepository, UserRepository userRepository, UserAuthenticationService userAuthenticationService) {
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
        this.userAuthenticationService = userAuthenticationService;
    }

    @Override
    @Transactional
    public void exchange(final MoneyExchangeRequest moneyExchangeRequest, final String token) {
        Currency from = currencyRepository.findById(moneyExchangeRequest.getFromCurrencyName())
                .orElseThrow(() -> new CurrencyNotFoundException(moneyExchangeRequest.getFromCurrencyName()));

        Currency to = currencyRepository.findById(moneyExchangeRequest.getToCurrencyName())
                .orElseThrow(() -> new CurrencyNotFoundException(moneyExchangeRequest.getToCurrencyName()));

        User user = userRepository.findByUsername(userAuthenticationService.validateTokenAndGetUsername(token)).orElseThrow(UserNotFoundException::new);
        user.exchange(new Money(from, moneyExchangeRequest.getAmount()), to);
    }

    @Override
    @Transactional
    public void send(final SendMoneyRequest request, final String authToken) {
        User userTo = userRepository.findByUsername(request.getToUser())
                .orElseThrow(() -> new UserNotFoundException(request.getToUser()));

        User userFrom = userRepository
                .findByUsername(userAuthenticationService.validateTokenAndGetUsername(authToken))
                .orElseThrow(UserNotFoundException::new);

        Currency currency = currencyRepository.findById(request.getCurrencyName())
                .orElseThrow(() -> new CurrencyNotFoundException(request.getCurrencyName()));

       new UserMoneyTransaction(userFrom, userTo,
                new Money(currency, request.getAmount())).doTransaction();
    }

    @Override
    public Map<String, Double> userBalance(String token) {
       return userRepository.findByUsername(userAuthenticationService.validateTokenAndGetUsername(token))
               .orElseThrow(UserNotFoundException::new).getBalance();
    }
}
