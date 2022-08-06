package com.bronze.boiler.domain.product.converter;

import com.bronze.boiler.domain.product.dto.ProductDto;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductImage;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConverter {

    public static ProductDto toProductDto(Product product, List<ProductImage> productImages) {

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .category(product.getCategory())
                .description(product.getDescription())
                .originPrice(product.getOriginPrice())
                .sellPrice(product.getSellPrice())
                .sellerInfo(product.getSellerInfo())
                .refundInfo(product.getRefundInfo())
                .savePoint(product.getSavePoint())
                .imaegUrls(productImages
                        .stream()
                        .filter(productImage -> !StringUtils.isEmpty(productImage.getDomain()))
                        .map(productImage -> productImage.getDomain().concat(productImage.getPath()))
                        .collect(Collectors.toList()))
                .sizeInfo(product.getSizeInfo())
                .status(product.getStatus())
                .build();
    }


    public static ProductDto toProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
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
                .name(productDto.getName())
                .code(productDto.getCode())
                .category(productDto.getCategory())
                .description(productDto.getDescription())
                .originPrice(productDto.getOriginPrice())
                .sellPrice(productDto.getSellPrice())
                .sellerInfo(productDto.getSellerInfo())
                .refundInfo(productDto.getRefundInfo())
                .savePoint(productDto.getSavePoint())
                .sizeInfo(productDto.getSizeInfo())
                .status(productDto.getStatus())
                .build();

    }
}
