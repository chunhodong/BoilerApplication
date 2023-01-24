package com.bronze.boiler.review.ui;

import com.bronze.boiler.review.domain.ReviewStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class ReviewFilter {

    private Long parentId;
    private ReviewStatus status;
}
