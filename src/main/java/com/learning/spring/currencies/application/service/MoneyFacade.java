package com.learning.spring.currencies.application.service;

import com.learning.spring.currencies.application.request.MoneyExchangeRequest;
import com.learning.spring.currencies.application.request.SendMoneyRequest;

import java.util.Map;

public interface MoneyFacade {

    void exchange(MoneyExchangeRequest moneyExchangeRequest, String token);
    void send(SendMoneyRequest sendMoneyRequest, String token);
    Map<String, Double> userBalance(String token);
}
