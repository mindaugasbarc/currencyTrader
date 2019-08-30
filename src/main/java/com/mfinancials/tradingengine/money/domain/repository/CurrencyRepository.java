package com.mfinancials.tradingengine.money.domain.repository;

import com.mfinancials.tradingengine.money.domain.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
