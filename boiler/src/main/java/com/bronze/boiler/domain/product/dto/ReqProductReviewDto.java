package com.bronze.boiler.domain.product.dto;


import com.bronze.boiler.domain.member.dto.ReqMemberDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 상품
 */
@AllArgsConstructor
@Builder
@Getter
public class ReqProductReviewDto {

    private Long id;

    private ReqMemberDto member;

    private String text;

    private ReqProductReviewDto parent;

}
