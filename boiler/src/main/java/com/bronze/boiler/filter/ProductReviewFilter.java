package com.bronze.boiler.filter;

import com.bronze.boiler.domain.product.enums.ProductReviewStatus;
import com.bronze.boiler.domain.product.enums.ProductStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class ProductReviewFilter {

    private Long parentId;
    private ProductReviewStatus status;


}
