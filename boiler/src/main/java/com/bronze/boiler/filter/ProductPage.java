package com.bronze.boiler.filter;

import com.bronze.boiler.domain.product.enums.ProductStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class ProductPage extends BasePage{

    private Long categoryId;
    private ProductStatus status;
}
