package com.bronze.boiler.service;


import com.bronze.boiler.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

}
