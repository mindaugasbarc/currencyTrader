package com.learning.spring.currencies.controller;

import com.learning.spring.currencies.repository.CurrencyRepository;
import com.learning.spring.currencies.exception.CurrencyNotFoundException;
import com.learning.spring.currencies.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping(path = "/currency")
public class CurrencyController {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyController(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @RequestMapping(path = "/find/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Currency getCurrency(@PathVariable("name") String name) {
        return currencyRepository.findById(name).orElseThrow(CurrencyNotFoundException::new);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCurrency(@RequestBody Currency currency) {
        currencyRepository.save(currency);
    }

    @RequestMapping(path = "/delete/{name}", method = RequestMethod.DELETE)
    public void deleteCurrency(@PathVariable("name") String name) {
        currencyRepository.deleteById(name);
    }
}
