package com.commerce.msproduct.mappers;

import com.commerce.msproduct.dto.ProductReqDto;
import com.commerce.msproduct.dto.ProductResDto;
import com.commerce.msproduct.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    ProductResDto todto(Product product);
    Product toEntity(ProductReqDto productReqDto);
    List<ProductResDto> toDtoList(List<Product> products);
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(ProductReqDto productReqDto, @MappingTarget Product product);
}
