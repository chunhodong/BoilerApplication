package com.bronze.boiler.repository;

import com.bronze.boiler.domain.product.entity.ProductReview;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductReviewRepositoryCst {
    List<ProductReview> findAllByPage(Pageable pageable);
}
