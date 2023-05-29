package com.microservices.currencyconversion.web;

import com.microservices.currencyconversion.CurrencyExchangeProxy;
import com.microservices.currencyconversion.entities.CurrencyConversion;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@AllArgsConstructor
public class CurrencyConversionController {
    private CurrencyExchangeProxy proxy;
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion CalulateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ){
        HashMap<String,String> uriVriables =new HashMap<>();
        uriVriables.put("from",from);
        uriVriables.put("to",to);
        ResponseEntity<CurrencyConversion> forEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class, uriVriables);
        CurrencyConversion currencyConversion = forEntity.getBody();
        return new CurrencyConversion(
                currencyConversion.getId(),
                from,
                to,
                quantity,
                currencyConversion.getConvertionMultiple(),
                quantity.multiply(currencyConversion.getConvertionMultiple()),
                currencyConversion.getEnvironment());
    }
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion CalulateCurrencyConversionfeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ){

        CurrencyConversion currencyConversion = proxy.getExchangeValue(from,to);
        return new CurrencyConversion(
                currencyConversion.getId(),
                from,
                to,
                quantity,
                currencyConversion.getConvertionMultiple(),
                quantity.multiply(currencyConversion.getConvertionMultiple()),
                currencyConversion.getEnvironment());
    }
}
