package com.bronze.boiler.review.dto;


import com.bronze.boiler.member.dto.MemberRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 상품
 */
@AllArgsConstructor
@Builder
@Getter
public class ProductReviewRequest {

    private Long id;

    private MemberRequest member;

    private String text;

    private ProductReviewRequest parent;

}
