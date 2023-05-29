package com.microservices.currencyexchangeservice.controller;

import com.microservices.currencyexchangeservice.beans.CurrentcyExchange;
import com.microservices.currencyexchangeservice.repositories.CurrentcyExchangeRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CurrentcyExchangeController {
    private CurrentcyExchangeRepository currentcyExchangeRepository;
    private Environment environment;
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrentcyExchange getExchangeValue(
            @PathVariable String from,
            @PathVariable String to
    ){
//        CurrentcyExchange currentcyExchange = new CurrentcyExchange(100L, from, to, BigDecimal.valueOf(50));
        CurrentcyExchange currentcyExchange =currentcyExchangeRepository.findByFromAndAndTo(from, to);
        if(currentcyExchange == null){
            throw new RuntimeException("Unable to find the data from "+from +" to "+to);
        }
        String port=environment.getProperty("local.server.port");
        currentcyExchange.setEnvironment(port);
        return currentcyExchange;
    }
}
