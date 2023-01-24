package com.bronze.boiler.product.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * 상품
 */
@AllArgsConstructor
@Builder
@Getter
public class ProductResponse {




    private Long id;

    private String name;

    private Long originPrice;

    private Long sellPrice;

    private List<String> imageUrls;






}
