package com.bronze.boiler.domain.category.dto;

import com.bronze.boiler.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
