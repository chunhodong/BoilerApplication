package com.bronze.boiler.domain.product.converter;

import com.bronze.boiler.domain.category.converter.CategoryConverter;
import com.bronze.boiler.domain.member.converter.MemberConverter;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.product.dto.*;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductImage;
import com.bronze.boiler.domain.product.entity.ProductOption;
import com.bronze.boiler.domain.product.entity.ProductReview;
import org.thymeleaf.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductReviewConverter {


    public static ProductReview toProductReview(ReqProductReviewDto productReviewDto) {

        return ProductReview.builder()
                .text(productReviewDto.getText())
                .member(Member.builder().id(productReviewDto.getMember().getId()).build())
                .parent(productReviewDto.getParent() == null
                        ? null
                        : ProductReview.builder().id(productReviewDto.getParent().getId()).build())
                .build();
    }


    public static ResProductReviewDto toProductReviewDto(ProductReview productReview) {
        return ResProductReviewDto.builder()
                .id(productReview.getId())
                .text(productReview.getText())
                .member(MemberConverter.toMemberDto(productReview.getMember()))
                .parent(productReview.getParent() == null
                                ? null
                                : toProductReviewDto(productReview.getParent()))
                .created(productReview.getCreated())
                .modified(productReview.getModified())
                .build();
    }
}
