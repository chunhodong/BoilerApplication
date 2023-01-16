package com.bronze.boiler.domain.order.converter;

import com.bronze.boiler.member.dto.MemberConverter;
import com.bronze.boiler.member.domain.Member;
import com.bronze.boiler.domain.order.dto.ReqOrderDto;
import com.bronze.boiler.domain.order.dto.ResOrderDto;
import com.bronze.boiler.domain.order.entity.OrderProduct;
import com.bronze.boiler.domain.order.entity.Orders;

import java.util.List;

public class OrderConverter {
    public static ResOrderDto toOrderDto(Orders order, List<OrderProduct> orderProduct) {

        return ResOrderDto.builder()
                .address(order.getAddress())
                .paymentPrice(order.getPaymentPrice())
                .discountPrice(order.getDiscountPrice())
                .member(MemberConverter.toMemberDto(order.getMember()))
                .status(order.getStatus())
                .title(orderProduct.stream()
                        .findFirst()
                        .map(op -> op.getProduct().getName())
                        .orElse(null))
                .build();
    }

    public static Orders toOrder(ReqOrderDto reqOrderDto, Member member,Long totalPrice) {

        return Orders.builder()
                .address(reqOrderDto.getAddress())
                .member(member)
                .status(reqOrderDto.getStatus())
                .totalPrice(totalPrice)
                .paymentPrice(reqOrderDto.getDiscountPrice() == null ? totalPrice : totalPrice - reqOrderDto.getDiscountPrice())
                .discountPrice(reqOrderDto.getDiscountPrice())
                .build();
    }
}
