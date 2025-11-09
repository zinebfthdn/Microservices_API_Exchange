package com.commerce.msachat.services;

import com.commerce.msachat.dto.*;
import com.commerce.msachat.entities.Achat;
import com.commerce.msachat.mappers.AchatMapper;
import com.commerce.msachat.repo.AchatRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AchatServiceImpl implements AchatService {

    private final AchatRepo achatRepo;
    private final AchatMapper achatMapper;

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${product.service.url:http://localhost:8080/api/product}")
    private String productServiceUrl;

    @Value("${exchange.api.url:https://open.er-api.com/v6/latest}")
    private String exchangeApiUrl;

    @Override
    public AchatResponseDTO addAchat(AchatRequestDTO requestDTO) {
        // 🔹 1. Récupérer les produits par leurs IDs
        List<ProductDTO> products = requestDTO.getProductIds().stream()
                .map(id -> restTemplate.getForObject(productServiceUrl + "/" + id, ProductDTO.class))
                .collect(Collectors.toList());

        // 🔹 2. Calculer le total en euro
        double totalEuro = products.stream().mapToDouble(ProductDTO::getPrice).sum();

        // 🔹 3. Récupérer le taux de change
        ExchangeDTO exchangeDTO = restTemplate.getForObject(exchangeApiUrl + "/EUR", ExchangeDTO.class);
        double taux = 1.0;

        if (exchangeDTO != null && exchangeDTO.getRates() != null) {
            taux = exchangeDTO.getRates().getOrDefault(requestDTO.getCurrency(), 1.0);
        }

        // 🔹 4. Calculer le total converti
        double totalConverted = totalEuro * taux;

        // 🔹 5. Enregistrer l’achat
        Achat achat = Achat.builder()
                .date(LocalDate.now())
                .currency(requestDTO.getCurrency())
                .productsId(requestDTO.getProductIds())
                .total(totalConverted)
                .build();

        Achat saved = achatRepo.save(achat);

        // 🔹 6. Mapper vers le DTO de réponse
        AchatResponseDTO response = achatMapper.toResponseDTO(saved);
        response.setProducts(products);
        response.setTotal(totalConverted);

        return response;
    }

    @Override
    public List<AchatResponseDTO> getAllAchats() {
        List<Achat> achats = achatRepo.findAll();

        return achats.stream().map(achat -> {
            AchatResponseDTO dto = achatMapper.toResponseDTO(achat);

            List<ProductDTO> products = achat.getProductsId().stream()
                    .map(id -> restTemplate.getForObject(productServiceUrl + "/" + id, ProductDTO.class))
                    .collect(Collectors.toList());

            dto.setProducts(products);
            return dto;
        }).collect(Collectors.toList());
    }
}
