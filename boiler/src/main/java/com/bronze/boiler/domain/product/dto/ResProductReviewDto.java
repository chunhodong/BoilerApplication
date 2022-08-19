package com.bronze.boiler.domain.product.dto;


import com.bronze.boiler.domain.member.dto.ResMemberDto;
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
public class ResProductReviewDto {




    private Long id;


    private ResMemberDto member;

    private String text;

    private ResProductReviewDto parent;

    private long childCount;

    private LocalDateTime created;

    private LocalDateTime modified;





}
