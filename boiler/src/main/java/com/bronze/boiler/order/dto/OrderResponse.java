package com.bronze.boiler.order.dto;

import com.bronze.boiler.member.dto.MemberResponse;
import com.bronze.boiler.domain.order.entity.Address;
import com.bronze.boiler.order.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {


    private OrderStatus status;

    private Long totalPrice;

    private Long discountPrice;

    private Long paymentPrice;

    private MemberResponse member;

    private Address address;

    private String title;
}
