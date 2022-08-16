package com.bronze.boiler.service;


import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.MemberExceptionType;
import com.bronze.boiler.domain.order.converter.OrderConverter;
import com.bronze.boiler.domain.order.converter.OrderProductConverter;
import com.bronze.boiler.domain.order.dto.ReqOrderDto;
import com.bronze.boiler.domain.order.dto.ResOrderDto;
import com.bronze.boiler.domain.order.entity.OrderProduct;
import com.bronze.boiler.domain.order.entity.Orders;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.exception.MemberException;
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

    public ResOrderDto createOrder(ReqOrderDto reqOrderDto) {


        Member member = memberRepository.findById(reqOrderDto.getMemberId())
                .orElseThrow(() -> new MemberException(MemberExceptionType.NONE_EXIST_MEMBER));

        List<Product> products = productRepository
                .findAllById(reqOrderDto.getProductMap().keySet())
                .stream().collect(Collectors.toList());

        Long totalPrice = products
                .stream()
                .mapToLong(value -> value.getSellPrice() * reqOrderDto.getProductMap().get(value.getId()))
                .sum();

        Orders order = orderRepository.save(OrderConverter.toOrder(reqOrderDto,member,totalPrice));

        List<OrderProduct> orderProducts = products.stream()
                .map(product -> OrderProductConverter
                        .toOrderProduct(order, product, reqOrderDto.getProductMap().get(product.getId())))
                .collect(Collectors.toList());

        orderProductRepository.saveAll(orderProducts);
        return OrderConverter.toOrderDto(order,orderProducts);
    }
}
