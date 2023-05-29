package com.microservices.limitsservices.Controller;

import com.microservices.limitsservices.beans.Limits;
import com.microservices.limitsservices.configuration.Configuration;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
public class LimitsController {
    private Configuration configuration;
    @GetMapping("/limits")
    public Limits getLimits(){
        return new Limits(configuration.getMinimum(),configuration.getMaximum());
    }
}
