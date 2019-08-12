package com.learning.spring.currencies.service.impl;

import com.learning.spring.currencies.request.SendMoneyRequestInternal;
import com.learning.spring.currencies.service.MoneyTransactionService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class MoneyTransactionServiceImpl implements MoneyTransactionService {

    @Override
    @Transactional
    public void doTransaction(SendMoneyRequestInternal request) {
        request.getUserFrom().getBalance().chargeMoney(request.getMoney());
        request.getUserTo().getBalance().addMoney(request.getMoney());
    }
}
