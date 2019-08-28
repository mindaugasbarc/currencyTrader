package com.learning.spring.currencies.service;

import com.learning.spring.currencies.request.MoneyExchangeRequest;
import com.learning.spring.currencies.request.SendMoneyRequest;

public interface MoneyFacade {

    void exchange(MoneyExchangeRequest moneyExchangeRequest, String token);
    void send(SendMoneyRequest sendMoneyRequest, String authToken);
}
