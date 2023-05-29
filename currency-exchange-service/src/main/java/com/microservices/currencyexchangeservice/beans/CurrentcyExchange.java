package com.microservices.currencyexchangeservice.beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrentcyExchange {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "currency_from")
    private String from;
    @Column(name = "currency_to")
    private String to;
    private BigDecimal convertionMultiple;
    private String environment;

    public CurrentcyExchange(Long id, String from, String to, BigDecimal convertionMultiple) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.convertionMultiple = convertionMultiple;
    }

}
