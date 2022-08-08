package com.bronze.boiler.domain.product.converter;

import com.bronze.boiler.domain.product.dto.ReqProductDto;
import com.bronze.boiler.domain.product.dto.ProductOptionDto;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductImage;
import com.bronze.boiler.domain.product.entity.ProductOption;
import org.thymeleaf.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductConverter {

    public static ReqProductDto toProductDto(Product product, List<ProductImage> productImages, List<ProductOption> productOptions) {

        return ReqProductDto.builder()
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
                .imageUrls(productImages == null ? Collections.EMPTY_LIST : productImages
                        .stream()
                        .filter(productImage -> !StringUtils.isEmpty(productImage.getDomain()))
                        .map(productImage -> productImage.getDomain().concat(productImage.getPath()))
                        .collect(Collectors.toList()))
                .options(productOptions == null ? Collections.EMPTY_LIST : productOptions
                        .stream()
                        .map(productOption -> ProductOptionDto
                                .builder()
                                .id(productOption.getId())
                                .type(productOption.getType())
                                .value(productOption.getValue())
                                .build())
                        .collect(Collectors.toList())
                )
                .sizeInfo(product.getSizeInfo())
                .status(product.getStatus())
                .build();
    }


    public static ReqProductDto toProductDto(Product product) {
        return ReqProductDto.builder()
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

    public static Product toProduct(ReqProductDto reqProductDto) {
        return Product.builder()
                .name(reqProductDto.getName())
                .code(reqProductDto.getCode())
                .category(reqProductDto.getCategory())
                .description(reqProductDto.getDescription())
                .originPrice(reqProductDto.getOriginPrice())
                .sellPrice(reqProductDto.getSellPrice())
                .sellerInfo(reqProductDto.getSellerInfo())
                .refundInfo(reqProductDto.getRefundInfo())
                .savePoint(reqProductDto.getSavePoint())
                .sizeInfo(reqProductDto.getSizeInfo())
                .status(reqProductDto.getStatus())
                .build();

    }
}
