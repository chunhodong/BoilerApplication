package com.bronze.boiler.order.application;


import com.bronze.boiler.order.dto.OrderRequest;
import com.bronze.boiler.order.dto.OrderResponse;
import com.bronze.boiler.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        return null;
    }

    public void cancelOrder(Long orderId) {
    }

    public void refundOrder(Long orderId) {

    }
}
