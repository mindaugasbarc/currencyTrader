package com.mfinancials.tradingengine.money.application.impl;

import com.mfinancials.tradingengine.money.application.MoneyFacade;
import com.mfinancials.tradingengine.money.domain.exception.UserNotFoundException;
import com.mfinancials.tradingengine.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MoneyFacadeImpl implements MoneyFacade {

    private final UserRepository userRepository;

    @Autowired
    public MoneyFacadeImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Map<String, Double> userBalance(String token) {
        return userRepository.findByUsername(token).orElseThrow(UserNotFoundException::new).getBalance();
    }
}
