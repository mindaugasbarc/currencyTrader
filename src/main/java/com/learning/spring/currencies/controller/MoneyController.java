package com.learning.spring.currencies.controller;

import com.learning.spring.currencies.model.Money;
import com.learning.spring.currencies.request.MoneyExchangeRequest;
import com.learning.spring.currencies.request.SendMoneyRequest;
import com.learning.spring.currencies.service.MoneyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/money")
public class MoneyController {

    private final MoneyFacade moneyFacade;

    @Autowired
    public MoneyController(MoneyFacade moneyFacade) {
        this.moneyFacade = moneyFacade;
    }

    @RequestMapping(path = "/exchange", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Money exchange(@RequestBody MoneyExchangeRequest moneyExchangeRequest) {
        return moneyFacade.exchange(moneyExchangeRequest);
    }

    @PostMapping("/send")
    public void sendMoney(@RequestBody SendMoneyRequest request, @RequestHeader("Authorization") String auth) {

    }
}
