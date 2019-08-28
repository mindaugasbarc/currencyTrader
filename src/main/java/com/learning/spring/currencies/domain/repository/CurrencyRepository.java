package com.learning.spring.currencies.domain.repository;

import com.learning.spring.currencies.domain.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
