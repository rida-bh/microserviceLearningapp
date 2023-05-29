package com.microservices.currencyexchangeservice.repositories;

import com.microservices.currencyexchangeservice.beans.CurrentcyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentcyExchangeRepository extends JpaRepository<CurrentcyExchange,Long> {
    CurrentcyExchange findByFromAndAndTo(String from, String to);
}
