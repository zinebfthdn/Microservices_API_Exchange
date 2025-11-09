package com.commerce.msproduct.web;

import com.commerce.msproduct.dto.ProductReqDto;
import com.commerce.msproduct.dto.ProductResDto;
import com.commerce.msproduct.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<ProductResDto> createProduct(@RequestBody ProductReqDto productReqDto) {
        ProductResDto createdProduct = productService.addProduct(productReqDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResDto>> getAllProducts() {
        List<ProductResDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResDto> getProductById(@PathVariable Long id) {
        ProductResDto product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResDto> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductReqDto productReqDto) {
        ProductResDto updatedProduct = productService.updateProduct(id, productReqDto);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
