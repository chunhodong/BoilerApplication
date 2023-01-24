package com.bronze.boiler.order.dto;

import com.bronze.boiler.domain.order.entity.Address;
import com.bronze.boiler.order.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {



    @NotNull
    private Long memberId;

    @NotNull
    private Map<Long,Long> productMap;

    @NotNull
    private Address address;

    private Long discountPrice;

    @NotNull
    private OrderStatus status;



}
