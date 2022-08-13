package com.bronze.boiler.repository;

import com.bronze.boiler.domain.product.entity.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview,Long>,ProductReviewRepositoryCst{


}
