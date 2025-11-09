package com.commerce.msproduct.services;

import com.commerce.msproduct.dto.ProductReqDto;
import com.commerce.msproduct.dto.ProductResDto;
import com.commerce.msproduct.entities.Product;
import com.commerce.msproduct.mappers.ProductMapper;
import com.commerce.msproduct.repositories.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
    final ProductRepo productRepo;
    final ProductMapper mapper;

    public ProductServiceImp(ProductRepo productRepo, ProductMapper mapper, ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.mapper = mapper;
    }
    @Override
    public ProductResDto addProduct(ProductReqDto productReqDto) {
        Product product = mapper.toEntity(productReqDto);
        Product savedProduct = productRepo.save(product);
        return mapper.todto(savedProduct);
    }
    @Override
    public ProductResDto getProductById(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return mapper.todto(product);
        }
        else{
            throw new EntityNotFoundException("Product not found");
        }
    }

    @Override
    public List<ProductResDto> getAllProducts() {
        return List.of();
    }

    @Override
    public ProductResDto updateProduct(Long id, ProductReqDto productReqDto) {
        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        mapper.updateEntityFromDto(productReqDto, existingProduct);
        Product updatedProduct = productRepo.save(existingProduct);
        return mapper.todto(updatedProduct);
    }
    @Override
    public void deleteProduct(Long id) {
        if (!productRepo.existsById(id)) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        productRepo.deleteById(id);
    }

}
