package com.mfinancials.tradingengine.money.infrastructure;

import com.mfinancials.tradingengine.money.application.MoneyFacade;
import com.mfinancials.tradingengine.money.application.request.MoneyExchangeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/exchange", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void exchange(@RequestBody MoneyExchangeRequest moneyExchangeRequest,
                         @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        moneyFacade.exchange(moneyExchangeRequest, token);
    }



}
