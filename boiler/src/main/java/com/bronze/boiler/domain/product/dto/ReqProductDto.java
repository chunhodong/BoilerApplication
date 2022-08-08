package com.bronze.boiler.domain.product.dto;


import com.bronze.boiler.domain.category.dto.CategoryDto;
import com.bronze.boiler.domain.category.entity.Category;
import com.bronze.boiler.domain.product.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 상품
 */
@AllArgsConstructor
@Builder
@Getter
public class ReqProductDto {




    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Long originPrice;

    @NotNull
    private Long sellPrice;

    private Long savePoint;

    @NotBlank
    private String code;

    @NotBlank
    private String description;

    private CategoryDto category;

    private ProductStatus status;

    @NotBlank
    private String sizeInfo;
    @NotBlank
    private String sellerInfo;
    @NotBlank
    private String refundInfo;

    @NotNull
    private List<String> imageUrls;

    @NotNull
    private List<ProductOptionDto> options;





}
