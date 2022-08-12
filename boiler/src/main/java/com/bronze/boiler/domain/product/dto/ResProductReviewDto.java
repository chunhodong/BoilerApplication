package com.bronze.boiler.domain.product.dto;


import com.bronze.boiler.domain.category.dto.CategoryDto;
import com.bronze.boiler.domain.member.dto.ResMemberDto;
import com.bronze.boiler.domain.product.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

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

    private LocalDateTime created;

    private LocalDateTime modified;





}
