package com.learning.spring.currencies;

import com.learning.spring.currencies.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
