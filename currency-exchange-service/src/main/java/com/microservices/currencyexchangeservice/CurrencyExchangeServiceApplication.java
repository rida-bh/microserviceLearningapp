package com.microservices.currencyexchangeservice;

import com.microservices.currencyexchangeservice.beans.CurrentcyExchange;
import com.microservices.currencyexchangeservice.repositories.CurrentcyExchangeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.stream.Stream;

@SpringBootApplication
public class CurrencyExchangeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner Start(CurrentcyExchangeRepository currentcyExchangeRepository){

        return args -> {
            int min = 50;
            int max = 100;
            Stream.of( "USA",  "EURO", "AED").forEach(currenty_from->{
                CurrentcyExchange currentcyExchange =new CurrentcyExchange();
                currentcyExchange.setFrom(currenty_from);
                currentcyExchange.setTo("MAD");
                currentcyExchange.setConvertionMultiple(BigDecimal.valueOf(Math.floor(Math.random() * (max - min + 1) + min)));
                currentcyExchangeRepository.save(currentcyExchange);
            });
        };
    }
}
