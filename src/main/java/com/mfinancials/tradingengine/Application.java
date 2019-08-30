package com.mfinancials.tradingengine;

import com.mfinancials.tradingengine.money.domain.model.Balance;
import com.mfinancials.tradingengine.money.domain.model.Currency;
import com.mfinancials.tradingengine.money.domain.model.Money;
import com.mfinancials.tradingengine.money.domain.repository.CurrencyRepository;
import com.mfinancials.tradingengine.user.domain.model.User;
import com.mfinancials.tradingengine.user.domain.repository.UserRepository;
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
        Currency usd = new Currency("USD", BigDecimal.ONE);
        Currency eur = new Currency("EUR", BigDecimal.valueOf(1.5));

        currencyRepository.save(usd);
        currencyRepository.save(eur);
        currencyRepository.save(new Currency("CAD", BigDecimal.valueOf(0.8)));

        Balance balance = new Balance();
        balance.addMoney(new Money(usd, 20));
        balance.addMoney(new Money(eur, 20));

        Balance jonasBalance = new Balance();
        jonasBalance.addMoney(new Money(usd, 100));
        jonasBalance.addMoney(new Money(eur, 100));
        userRepository.save(new User("minde", balance));
        userRepository.save(new User("jonas", jonasBalance));
    }
}
