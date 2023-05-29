package com.microservices.limitsservices.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Limits {
    private int minimum;
    private int maximum;
}
