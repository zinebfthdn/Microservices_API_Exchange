package com.commerce.msachat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AchatRequestDTO {
    private List<Long> productIds;
    private String currency;
}
