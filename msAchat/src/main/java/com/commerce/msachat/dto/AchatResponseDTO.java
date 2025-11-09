package com.commerce.msachat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AchatResponseDTO {
    private Long id;
    private LocalDate date;
    private String currency;
    private double total;
    private List<ProductDTO> products;
}
