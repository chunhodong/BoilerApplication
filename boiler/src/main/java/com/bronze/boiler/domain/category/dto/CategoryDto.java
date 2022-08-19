package com.bronze.boiler.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 상품카테고리
 */
@AllArgsConstructor
@Builder
@Getter
public class CategoryDto {

    private Long id;


    private String name;

    private CategoryDto parent;


}
