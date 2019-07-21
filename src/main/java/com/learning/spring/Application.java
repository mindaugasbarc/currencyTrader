package com.learning.spring;

import com.learning.spring.currencies.Currency;
import com.learning.spring.currencies.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CurrencyRepository currencyRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        currencyRepository.save(new Currency("USD", BigDecimal.valueOf(1)));
        currencyRepository.save(new Currency("EUR", BigDecimal.valueOf(1.5)));
        currencyRepository.save(new Currency("CAD", BigDecimal.valueOf(0.8)));
    }
}
