package com.bronze.boiler.domain.order.converter;

import com.bronze.boiler.domain.member.converter.MemberConverter;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.order.dto.ReqOrderDto;
import com.bronze.boiler.domain.order.dto.ResOrderDto;
import com.bronze.boiler.domain.order.entity.OrderProduct;
import com.bronze.boiler.domain.order.entity.Orders;
import com.bronze.boiler.domain.product.entity.Product;

import java.util.List;

public class OrderProductConverter {


    public static OrderProduct toOrderProduct(Orders order, Product product, Long count) {
        return OrderProduct
                .builder()
                .count(count)
                .product(product)
                .order(order)
                .build();
    }
}
