package com.bronze.boiler.service;


import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.MemberExceptionType;
import com.bronze.boiler.domain.order.converter.OrderConverter;
import com.bronze.boiler.domain.order.converter.OrderProductConverter;
import com.bronze.boiler.domain.order.dto.ReqOrderDto;
import com.bronze.boiler.domain.order.dto.ResOrderDto;
import com.bronze.boiler.domain.order.entity.Address;
import com.bronze.boiler.domain.order.entity.OrderProduct;
import com.bronze.boiler.domain.order.entity.Orders;
import com.bronze.boiler.domain.order.enums.OrderExceptionType;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductStock;
import com.bronze.boiler.domain.product.enums.ProductExceptionType;
import com.bronze.boiler.exception.MemberException;
import com.bronze.boiler.exception.OrderException;
import com.bronze.boiler.exception.ProductException;
import com.bronze.boiler.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private OrderProductRepository orderProductRepository;
    private ProductRepository productRepository;
    private MemberRepository memberRepository;
    private ProductStockRepository productStockRepository;

    /**
     * 주문하기
     * @param reqOrderDto 주문DTO
     * @return 주문결과
     */
    public ResOrderDto createOrder(ReqOrderDto reqOrderDto) {
        List<Product> products = productRepository
                .findAllById(reqOrderDto.getProductMap().keySet())
                .stream().collect(Collectors.toList());
        List<ProductStock> productStocks = productStockRepository.findAllByProductIn(products);
        productStocks.forEach(productStock -> {
            if(!productStock.isRemainCurrentStock())
                throw new ProductException(ProductExceptionType.SOLDOUT_PRODUCT);
        });
        Long totalPrice = products
                .stream()
                .mapToLong(value -> value.getSellPrice() * reqOrderDto.getProductMap().get(value.getId()))
                .sum();
        Member member = memberRepository.findById(reqOrderDto.getMemberId())
                .orElseThrow(() -> new MemberException(MemberExceptionType.NONE_EXIST_MEMBER));
        Orders order = orderRepository.save(OrderConverter.toOrder(reqOrderDto,member,totalPrice));
        List<OrderProduct> orderProducts = products.stream()
                .map(product -> OrderProductConverter
                        .toOrderProduct(order, product, reqOrderDto.getProductMap().get(product.getId())))
                .collect(Collectors.toList());
        orderProductRepository.saveAll(orderProducts);
        productStocks.forEach(productStock -> productStock.minusCurrentStock());
        return OrderConverter.toOrderDto(order,orderProducts);
    }

    public void cancelOrder(Long orderId){
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(OrderExceptionType.NONE_EXIST_ORDER));
        order.cancel();

    }

    public void refundOrder(Long orderId){
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(OrderExceptionType.NONE_EXIST_ORDER));
        order.refund();

    }

    public void modifyAddress(Long orderId,Address address) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(OrderExceptionType.NONE_EXIST_ORDER));
        order.modifyAddress(address);

    }
}
