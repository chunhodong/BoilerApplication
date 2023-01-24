package com.bronze.boiler.review.dto;


import com.bronze.boiler.member.dto.MemberResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 상품
 */
@AllArgsConstructor
@Builder
@Getter
public class ProductReviewResponse {




    private Long id;


    private MemberResponse member;

    private String text;

    private ProductReviewResponse parent;

    private long childCount;

    private LocalDateTime created;

    private LocalDateTime modified;





}
