package com.learning.spring.currencies.service;

import com.learning.spring.currencies.request.SendMoneyRequestInternal;

public interface MoneyTransactionService {

    void doTransaction(SendMoneyRequestInternal request);
}
