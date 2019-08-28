package com.learning.spring.currencies.service.impl;

import com.learning.spring.currencies.request.SendMoneyRequestInternal;
import com.learning.spring.currencies.service.MoneyTransactionService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;


@Component
public class MoneyTransactionServiceImpl implements MoneyTransactionService {

    @Override
    @Transactional
    public void doTransaction(final SendMoneyRequestInternal request) {
        request.getUserFrom().chargeMoney(request.getMoney());
        request.getUserTo().receiveMoney(request.getMoney());
    }
}
