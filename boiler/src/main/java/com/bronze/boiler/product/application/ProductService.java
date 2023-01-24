package com.bronze.boiler.product.application;


import com.bronze.boiler.product.dto.ProductDetailResponse;
import com.bronze.boiler.product.dto.ProductRequest;
import com.bronze.boiler.product.repository.ProductRepository;
import com.bronze.boiler.review.dto.ProductReviewRequest;
import com.bronze.boiler.review.dto.ProductReviewResponse;
import com.bronze.boiler.review.repository.ProductReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductReviewRepository productReviewRepository;

    public ProductDetailResponse createProduct(ProductRequest productRequest) {
        return null;
    }

    public ProductDetailResponse getProduct(Long productId) {
        return null;
    }

    public void modifyProduct(ProductRequest productRequest) {}

    public ProductReviewResponse createProductReview(ProductReviewRequest productReviewRequest) {
        return null;
    }
}

