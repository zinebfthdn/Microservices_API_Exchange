package com.commerce.msproduct.services;

import com.commerce.msproduct.dto.ProductReqDto;
import com.commerce.msproduct.dto.ProductResDto;

import java.util.List;

public interface ProductService {
    ProductResDto addProduct(ProductReqDto productReqDto);
    ProductResDto getProductById(Long id);
    List<ProductResDto> getAllProducts();
    ProductResDto updateProduct(Long id, ProductReqDto productReqDto);
    void deleteProduct(Long id);
}
