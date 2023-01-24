package com.bronze.boiler.product.dto;


import com.bronze.boiler.product.domain.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@AllArgsConstructor
@Builder
@Getter
public class ProductDetailResponse {

    private Long id;
    private String name;
    private Long originPrice;
    private Long sellPrice;
    private Long savePoint;
    private String code;
    private String description;
    private ProductStatus status;
    private String sizeInfo;
    private String sellerInfo;
    private String refundInfo;
    private List<String> imageUrls;
}
