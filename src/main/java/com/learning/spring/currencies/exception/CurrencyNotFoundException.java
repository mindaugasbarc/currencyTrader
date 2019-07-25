package com.learning.spring.currencies.exception;

public class CurrencyNotFoundException extends RuntimeException {

    public CurrencyNotFoundException() {
    }

    public CurrencyNotFoundException(String name) {
        super("currency was not found: " + name);
    }
}
