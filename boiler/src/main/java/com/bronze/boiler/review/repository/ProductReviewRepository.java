package com.bronze.boiler.review.repository;

import com.bronze.boiler.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReviewRepository extends JpaRepository<Review,Long>,ProductReviewRepositoryCst{


}
