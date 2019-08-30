package com.mfinancials.tradingengine.money.infrastructure;

import com.mfinancials.tradingengine.money.application.MoneyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/money")
public class MoneyController {

    private final MoneyFacade moneyFacade;

    @Autowired
    public MoneyController(MoneyFacade moneyFacade) {
        this.moneyFacade = moneyFacade;
    }

    @GetMapping("/balance")
    public Map<String, Double> balance(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return moneyFacade.userBalance(token);
    }



}
