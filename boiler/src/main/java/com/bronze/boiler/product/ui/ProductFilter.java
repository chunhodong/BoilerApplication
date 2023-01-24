package com.bronze.boiler.product.ui;

import com.bronze.boiler.product.domain.ProductStatus;
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
