package com.commerce.msachat.mappers;

import com.commerce.msachat.dto.AchatResponseDTO;
import com.commerce.msachat.entities.Achat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AchatMapper {

    @Mapping(target = "products", ignore = true)
    AchatResponseDTO toResponseDTO(Achat achat);
}
