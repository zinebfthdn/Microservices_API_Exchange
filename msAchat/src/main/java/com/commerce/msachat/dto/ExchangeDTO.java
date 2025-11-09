package com.commerce.msachat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeDTO {
    private String result;
    private String base_code;
    private Map<String, Double> rates;
}
