package com.bronze.boiler.service;


import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.order.dto.ReqOrderDto;
import com.bronze.boiler.domain.order.dto.ResOrderDto;
import com.bronze.boiler.domain.order.entity.OrderProduct;
import com.bronze.boiler.domain.order.entity.Orders;
import com.bronze.boiler.domain.product.converter.ProductConverter;
import com.bronze.boiler.domain.product.converter.ProductReviewConverter;
import com.bronze.boiler.domain.product.dto.*;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductImage;
import com.bronze.boiler.domain.product.entity.ProductOption;
import com.bronze.boiler.domain.product.entity.ProductReview;
import com.bronze.boiler.domain.product.enums.ProductExceptionType;
import com.bronze.boiler.domain.product.enums.ProductReviewExceptionType;
import com.bronze.boiler.exception.ProductException;
import com.bronze.boiler.exception.ProductReviewException;
import com.bronze.boiler.filter.ProductFilter;
import com.bronze.boiler.filter.ProductReviewFilter;
import com.bronze.boiler.repository.*;
import com.bronze.boiler.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private OrderProductRepository orderProductRepository;
    private ProductRepository productRepository;

    public ResOrderDto createOrder(ReqOrderDto reqOrderDto) {

     /*
        Long totalPrice = productRepository.findAllById(reqOrderDto.getProductMap().keySet())
                .stream()
                .mapToLong(value -> value.getSellPrice() * reqOrderDto.getProductMap().get(value.getId()))
                .sum();


        Orders order = orderRepository.save(Orders.builder()
                        .address(reqOrderDto.getAddress())
                        .member(Member.builder().id(reqOrderDto.getMemberId()).build())
                        .status(reqOrderDto.getStatus())
                        .totalPrice(totalPrice)
                        .paymentPrice(totalPrice - reqOrderDto.getDiscountPrice())
                        .discountPrice(reqOrderDto.getDiscountPrice())
                .build());

        orderProductRepository.save(OrderProduct.builder()
                        .product()
                        .count()
                .build());
*/
        return null;
    }
}
