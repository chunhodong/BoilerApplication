package com.bronze.boiler.filter;

import com.bronze.boiler.domain.product.enums.ProductStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class ProductFilter{

    private Long categoryId;
    private ProductStatus status;
}
