package com.bronze.boiler.domain.order.converter;


import com.bronze.boiler.domain.order.entity.OrderProduct;
import com.bronze.boiler.domain.order.entity.Orders;
import com.bronze.boiler.domain.product.entity.Product;

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
