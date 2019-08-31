package com.mfinancials.tradingengine.money.domain.exception;

public class CurrencyNotFoundException extends RuntimeException {

    public CurrencyNotFoundException(String currencyName) {
        super("currency with name: " + currencyName + " not found");
    }
}
