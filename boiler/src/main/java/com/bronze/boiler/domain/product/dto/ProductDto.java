package com.bronze.boiler.domain.product.dto;


import com.bronze.boiler.domain.category.entity.Category;
import com.bronze.boiler.domain.product.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 상품
 */
@AllArgsConstructor
@Builder
@Getter
public class ProductDto {




    private Long id;

    private String name;

    private Long originPrice;

    private Long sellPrice;

    private Long savePoint;

    private String code;

    private String description;

    private Category category;

    private ProductStatus status;

    private String sizeInfo;

    private String sellerInfo;

    private String refundInfo;

    private List<String> imaegUrls;

    private List<ProductOptionDto> options;





}
