package com.bronze.boiler.domain.order.dto;

import com.bronze.boiler.domain.order.entity.Address;
import com.bronze.boiler.domain.order.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResOrderDto {


    private OrderStatus status;

    private Long totalPrice;

    private Long discountPrice;

    private Long paymentPrice;

    private Long memberId;

    private Address address;
}
