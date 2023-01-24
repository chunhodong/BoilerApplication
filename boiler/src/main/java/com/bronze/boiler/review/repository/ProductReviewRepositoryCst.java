package com.bronze.boiler.review.repository;

import com.bronze.boiler.review.domain.Review;
import com.bronze.boiler.review.ui.ReviewFilter;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductReviewRepositoryCst {
    List<Review> findAllByPage(ReviewFilter filter, Pageable pageable);

    List<Review> findAllWithFetchJoin();

    List<Review> findAllWithOffset(Long offset, Long limit);

    List<Review> findAllWithNoOffset(Long id, Long limit);
}
