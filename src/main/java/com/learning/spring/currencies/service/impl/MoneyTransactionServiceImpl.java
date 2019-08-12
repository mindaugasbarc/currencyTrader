package com.learning.spring.currencies.service.impl;

import com.learning.spring.currencies.request.SendMoneyRequestInternal;
import com.learning.spring.currencies.service.MoneyTransactionService;

import javax.transaction.Transactional;

public class MoneyTransactionServiceImpl implements MoneyTransactionService {

    @Override
    @Transactional
    public void doTransaction(SendMoneyRequestInternal request) {
        request.getUserTo().getBalance().chargeMoney(request.getMoney());
        request.getUserTo().getBalance().addMoney(request.getMoney());
    }
}
