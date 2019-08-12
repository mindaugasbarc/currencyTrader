package com.learning.spring.currencies.service;

import com.learning.spring.currencies.request.SendMoneyRequestInternal;
import com.learning.spring.user.model.User;

public interface MoneyTransactionService {

    void doTransaction(SendMoneyRequestInternal sendMoneyRequestInternal);
}
