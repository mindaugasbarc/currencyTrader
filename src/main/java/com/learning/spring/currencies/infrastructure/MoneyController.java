package com.learning.spring.currencies.infrastructure;

import com.learning.spring.currencies.application.request.MoneyExchangeRequest;
import com.learning.spring.currencies.application.request.SendMoneyRequest;
import com.learning.spring.currencies.application.service.MoneyFacade;
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

    @RequestMapping(path = "/exchange", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void exchange(@RequestBody MoneyExchangeRequest moneyExchangeRequest, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        moneyFacade.exchange(moneyExchangeRequest, token);
    }

    @PostMapping("/send")
    public void sendMoney(@RequestBody SendMoneyRequest request, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        moneyFacade.send(request, token);
    }

    @GetMapping("/balance")
    public Map<String, Double> balance(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return moneyFacade.userBalance(token);
    }
}
