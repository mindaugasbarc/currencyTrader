package com.mfinancials.tradingengine.money.application.impl;

import com.mfinancials.tradingengine.money.application.MoneyFacade;
import com.mfinancials.tradingengine.money.application.request.MoneyExchangeRequest;
import com.mfinancials.tradingengine.money.domain.exception.CurrencyNotFoundException;
import com.mfinancials.tradingengine.money.domain.exception.UserNotFoundException;
import com.mfinancials.tradingengine.money.domain.model.Currency;
import com.mfinancials.tradingengine.money.domain.model.Money;
import com.mfinancials.tradingengine.money.domain.repository.CurrencyRepository;
import com.mfinancials.tradingengine.money.domain.service.UserAuthenticationService;
import com.mfinancials.tradingengine.user.domain.model.User;
import com.mfinancials.tradingengine.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Map;

@Component
public class MoneyFacadeImpl implements MoneyFacade {

    private final UserRepository userRepository;
    private final UserAuthenticationService userAuthenticationService;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public MoneyFacadeImpl(UserRepository userRepository, UserAuthenticationService userAuthenticationService, CurrencyRepository currencyRepository) {
        this.userRepository = userRepository;
        this.userAuthenticationService = userAuthenticationService;
        this.currencyRepository = currencyRepository;
    }

    @Override
    @Transactional
    public void exchange(MoneyExchangeRequest moneyExchangeRequest, String token) {
        Currency from = findCurrency(moneyExchangeRequest.getFromCurrencyName());
        Currency to = findCurrency(moneyExchangeRequest.getToCurrencyName());
        User user = findUserByToken(token);
        user.exchange(new Money(from, moneyExchangeRequest.getAmount()), to);
    }

    @Override
    public Map<String, Double> userBalance(String token) {
        return findUserByToken(token).getBalance();
    }

    private User findUserByToken(String token) {
        return userRepository.findByUsername(userAuthenticationService.validateTokenAndGetUsername(token))
                .orElseThrow(UserNotFoundException::new);
    }

    private Currency findCurrency(String currencyName) {
        return currencyRepository.findById(currencyName)
                .orElseThrow(() -> new CurrencyNotFoundException(currencyName));
    }
}
