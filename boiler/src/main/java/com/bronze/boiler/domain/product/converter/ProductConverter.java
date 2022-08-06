package com.bronze.boiler.domain.product.converter;

import com.bronze.boiler.domain.product.dto.ProductDto;
import com.bronze.boiler.domain.product.entity.Product;

public class ProductConverter {



    public static ProductDto toProductDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .code(product.getCode())
                .category(product.getCategory())
                .description(product.getDescription())
                .originPrice(product.getOriginPrice())
                .sellPrice(product.getSellPrice())
                .sellerInfo(product.getSellerInfo())
                .refundInfo(product.getRefundInfo())
                .savePoint(product.getSavePoint())
                .sizeInfo(product.getSizeInfo())
                .status(product.getStatus())
                .build();
    }

    public static Product toProduct(ProductDto productDto) {


        return Product.builder()
                .build();

    }
}
