package com.learning.spring.currencies.service;

import com.learning.spring.currencies.model.Money;
import com.learning.spring.currencies.request.MoneyExchangeRequest;

public interface MoneyFacade {

    Money exchange(MoneyExchangeRequest moneyExchangeRequest);
}
