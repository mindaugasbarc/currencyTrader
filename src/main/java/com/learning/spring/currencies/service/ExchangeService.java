package com.learning.spring.currencies.service;

import com.learning.spring.currencies.model.Currency;
import com.learning.spring.currencies.model.Money;

public interface ExchangeService {

    Money exchange(Money money, Currency to);
}
