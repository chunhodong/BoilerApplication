package com.bronze.boiler.service;

import com.bronze.boiler.domain.category.entity.Category;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.MemberExceptionType;
import com.bronze.boiler.domain.member.enums.MemberStatus;
import com.bronze.boiler.domain.member.enums.Role;
import com.bronze.boiler.domain.order.dto.ReqOrderDto;
import com.bronze.boiler.domain.order.dto.ResOrderDto;
import com.bronze.boiler.domain.order.entity.Address;
import com.bronze.boiler.domain.order.entity.Orders;
import com.bronze.boiler.domain.order.enums.OrderStatus;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.enums.ProductExceptionType;
import com.bronze.boiler.domain.product.enums.ProductStatus;
import com.bronze.boiler.exception.MemberException;
import com.bronze.boiler.exception.ProductException;
import com.bronze.boiler.repository.MemberRepository;
import com.bronze.boiler.repository.OrderProductRepository;
import com.bronze.boiler.repository.OrderRepository;
import com.bronze.boiler.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//테스트실행 확장을 위해 추가하는 Annotation
@ExtendWith(SpringExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderProductRepository orderProductRepository;

    @Test
    void 주문추가_없는회원일때_예외발생() {
        doReturn(Optional.empty()).when(memberRepository).findById(any());
        ReqOrderDto reqOrderDto = ReqOrderDto.builder().build();
        MemberException exception = assertThrows(MemberException.class, () -> orderService.createOrder(reqOrderDto));
        assertThat(exception.getType()).isEqualTo(MemberExceptionType.NONE_EXIST_MEMBER);
    }

    @Test
    void 주문추가_품절상품일때_예외발생() {

        ReqOrderDto reqOrderDto = ReqOrderDto.builder()
                .productMap(Map.of(3l,1l,2l,1l))
                .status(OrderStatus.PAYMENT)
                .address(Address.builder().titleAddress("강남구").zipcode(50005l).build())
                .build();

        doReturn(Optional.ofNullable(Member.builder()
                .id(13L)
                .name("유저")
                .status(MemberStatus.NORMAL)
                .email("email@email.com")
                .role(Role.USER)
                .password("test1234")
                .build())).when(memberRepository).findById(any());

        doReturn(List.of(
                Product.builder()
                        .id(3L)
                        .name("상품3")
                        .originPrice(130000L)
                        .sellPrice(120000L)
                        .sellerInfo("판매자정보3")
                        .code("FOIWIO0023")
                        .status(ProductStatus.SOLDOUT)
                        .sizeInfo("사이즈정보3")
                        .description("판매상품설명3")
                        .refundInfo("환불정보3")
                        .savePoint(1000L)
                        .category(Category.builder().id(1l).name("패션").build())
                        .build(),
                Product.builder()
                        .id(2L)
                        .name("상품2")
                        .originPrice(130000L)
                        .sellPrice(120000L)
                        .sellerInfo("판매자정보2")
                        .code("FOIWIO0023")
                        .status(ProductStatus.NEW)
                        .sizeInfo("사이즈정보2")
                        .description("판매상품설명2")
                        .refundInfo("환불정보2")
                        .savePoint(1000L)
                        .category(Category.builder().id(1l).name("패션").build()).build()
        ))
                .when(productRepository)
                .findAllById(anySet());

        ProductException exception = assertThrows(ProductException.class, () -> orderService.createOrder(reqOrderDto));
        assertThat(exception.getType()).isEqualTo(ProductExceptionType.SOLDOUT_PRODUCT);
    }

    @Test
    void 주문추가_주문확인() {
        ReqOrderDto reqOrderDto = ReqOrderDto.builder()
                .productMap(Map.of(3l,1l,2l,1l))
                .status(OrderStatus.PAYMENT)
                .address(Address.builder().titleAddress("강남구").zipcode(50005l).build())
                .build();

        doReturn(Optional.ofNullable(Member.builder()
                .id(13L)
                .name("유저")
                .status(MemberStatus.NORMAL)
                .email("email@email.com")
                .role(Role.USER)
                .password("test1234")
                .build())).when(memberRepository).findById(any());

        doReturn(List.of(
                Product.builder()
                        .id(3L)
                        .name("상품3")
                        .originPrice(130000L)
                        .sellPrice(120000L)
                        .sellerInfo("판매자정보3")
                        .code("FOIWIO0023")
                        .status(ProductStatus.NEW)
                        .sizeInfo("사이즈정보3")
                        .description("판매상품설명3")
                        .refundInfo("환불정보3")
                        .savePoint(1000L)
                        .category(Category.builder().id(1l).name("패션").build())
                        .build(),
                Product.builder()
                        .id(2L)
                        .name("상품2")
                        .originPrice(130000L)
                        .sellPrice(120000L)
                        .sellerInfo("판매자정보2")
                        .code("FOIWIO0023")
                        .status(ProductStatus.NEW)
                        .sizeInfo("사이즈정보2")
                        .description("판매상품설명2")
                        .refundInfo("환불정보2")
                        .savePoint(1000L)
                        .category(Category.builder().id(1l).name("패션").build()).build()
                ))
                .when(productRepository)
                .findAllById(anySet());



        doReturn(Orders.builder()
                .status(OrderStatus.PAYMENT)
                .totalPrice(240000l)
                .member(Member.builder().id(13l).build())
                .paymentPrice(240000l)
                .address(Address.builder().titleAddress("강남구").zipcode(50050l).build())
                .build()).when(orderRepository).save(any());

        doReturn(List.of()).when(orderProductRepository).saveAll(any());



        ResOrderDto orderDto = orderService.createOrder(reqOrderDto);
        assertThat(orderDto.getTitle()).isEqualTo("상품3");

    }


    @Test
    void 주문취소(){
        Orders orders = Orders.builder().status(OrderStatus.PAYMENT).build();
        doReturn(Optional.ofNullable(orders))
                .when(orderRepository).findById(anyLong());
        orderService.cancelOrder(13L);
        assertThat(orders.getStatus()).isEqualTo(OrderStatus.CANCEL);
    }

    @Test
    void 주문환불(){
        Orders orders = Orders.builder().status(OrderStatus.PAYMENT).build();
        doReturn(Optional.ofNullable(orders))
                .when(orderRepository).findById(anyLong());
        orderService.refundOrder(13L);
        assertThat(orders.getStatus()).isEqualTo(OrderStatus.REFUND);
    }

    @Test
    void 주문수정_주소변경시_주문확인(){
        Orders orders = Orders.builder().address(Address.builder().titleAddress("강동구1").build()).build();
        doReturn(Optional.ofNullable(orders))
                .when(orderRepository).findById(anyLong());
        orderService.modifyAddress(12l,Address.builder().titleAddress("성북구123").build());
        assertThat(orders.getAddress().getTitleAddress()).isEqualTo("성북구123");
    }



}
