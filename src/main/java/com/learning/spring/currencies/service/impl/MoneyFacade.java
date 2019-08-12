package com.learning.spring.currencies.service.impl;

import com.learning.spring.currencies.model.Money;
import com.learning.spring.currencies.request.MoneyExchangeRequest;
import com.learning.spring.currencies.request.SendMoneyRequest;

public interface MoneyFacade {

    Money exchange(MoneyExchangeRequest moneyExchangeRequest);

    void send(SendMoneyRequest sendMoneyRequest, String authToken);
}
