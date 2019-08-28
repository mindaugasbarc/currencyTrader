package com.learning.spring.currencies.domain.exception;

public class CurrencyNotFoundException extends RuntimeException {

    public CurrencyNotFoundException() {
    }

    public CurrencyNotFoundException(String name) {
        super("currency was not found: " + name);
    }
}
