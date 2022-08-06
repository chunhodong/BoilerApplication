package com.bronze.boiler.service;


import com.bronze.boiler.domain.product.converter.ProductConverter;
import com.bronze.boiler.domain.product.dto.ProductDto;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDto createProduct(ProductDto productDto) {
        Product product = productRepository.save(ProductConverter.toProduct(productDto));
        return ProductConverter.toProductDto(product);
    }

}
