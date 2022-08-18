package com.bronze.boiler.domain.product.dto;


import com.bronze.boiler.domain.product.enums.OptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 상품옵션
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionDto {

    private Long id;

    private OptionType type;

    private String value;



}
