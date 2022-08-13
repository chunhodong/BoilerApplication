package com.bronze.boiler.repository;

import com.bronze.boiler.domain.product.entity.ProductReview;
import com.bronze.boiler.filter.ProductReviewFilter;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductReviewRepositoryCst {
    List<ProductReview> findAllByPage(ProductReviewFilter filter,Pageable pageable);
}
