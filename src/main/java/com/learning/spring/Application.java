package com.learning.spring;

import com.learning.spring.currencies.model.Balance;
import com.learning.spring.currencies.model.Currency;
import com.learning.spring.currencies.model.Money;
import com.learning.spring.currencies.repository.CurrencyRepository;
import com.learning.spring.user.UserRepository;
import com.learning.spring.user.model.User;
import com.learning.spring.user.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
	    Currency usd = new Currency("USD", BigDecimal.valueOf(1));
	    Currency eur = new Currency("EUR", BigDecimal.valueOf(1.5));

        currencyRepository.save(usd);
        currencyRepository.save(eur);
        currencyRepository.save(new Currency("CAD", BigDecimal.valueOf(0.8)));

        Balance balance = new Balance();
        balance.addMoney(new Money(usd, 20));
        balance.addMoney(new Money(eur, 20));

        Balance balance2 = new Balance();
        balance2.addMoney(new Money(usd, 100));
        balance2.addMoney(new Money(eur, 100));
        userRepository.save(new User(new UserDetailsImpl("minde", "1234"), balance));
        userRepository.save(new User(new UserDetailsImpl("jonas", "4321"), balance2));


    }
}
