package com.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/simple-api")
    //@Retry(name = "simple-api",fallbackMethod = "errorReporter")
    //@CircuitBreaker(name = "default",fallbackMethod = "errorReporter")
    @RateLimiter(name = "default")
    public String simpleApi(){
        logger.info("Simple Api call received");
/*        ResponseEntity<String> forEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/some", String.class);*/
        return "forEntity.getBody()";
    }
    public String errorReporter(Exception exception){
        return "FallbackError = "+exception.getMessage();
    }
}
