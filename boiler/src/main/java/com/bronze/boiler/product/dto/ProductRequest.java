package com.bronze.boiler.product.dto;


import com.bronze.boiler.product.domain.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@AllArgsConstructor
@Builder
@Getter
public class ProductRequest {
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
    private ProductStatus status;
    @NotBlank
    private String sizeInfo;
    @NotBlank
    private String sellerInfo;
    @NotBlank
    private String refundInfo;
    @NotNull
    private List<String> imageUrls;
}
