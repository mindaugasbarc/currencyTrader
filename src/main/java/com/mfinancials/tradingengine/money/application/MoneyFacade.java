package com.mfinancials.tradingengine.money.application;

import com.mfinancials.tradingengine.money.application.request.MoneyExchangeRequest;

import java.util.Map;

public interface MoneyFacade {

    public void exchange(MoneyExchangeRequest moneyExchangeRequest, String token);
    public Map<String, Double> userBalance(String token);
}
