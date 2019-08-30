package com.mfinancials.tradingengine.money.application;

import java.util.Map;

public interface MoneyFacade {

    public Map<String, Double> userBalance(String token);
}
