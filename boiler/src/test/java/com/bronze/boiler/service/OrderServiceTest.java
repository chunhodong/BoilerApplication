package com.bronze.boiler.service;

import com.bronze.boiler.domain.category.dto.CategoryDto;
import com.bronze.boiler.domain.category.entity.Category;
import com.bronze.boiler.domain.member.dto.ReqMemberDto;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.MemberExceptionType;
import com.bronze.boiler.domain.member.enums.MemberStatus;
import com.bronze.boiler.domain.member.enums.Role;
import com.bronze.boiler.domain.order.dto.ReqOrderDto;
import com.bronze.boiler.domain.order.dto.ResOrderDto;
import com.bronze.boiler.domain.order.entity.Address;
import com.bronze.boiler.domain.order.entity.Orders;
import com.bronze.boiler.domain.order.enums.OrderStatus;
import com.bronze.boiler.domain.product.dto.*;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductImage;
import com.bronze.boiler.domain.product.entity.ProductOption;
import com.bronze.boiler.domain.product.entity.ProductReview;
import com.bronze.boiler.domain.product.enums.*;
import com.bronze.boiler.exception.MemberException;
import com.bronze.boiler.exception.ProductException;
import com.bronze.boiler.exception.ProductReviewException;
import com.bronze.boiler.filter.ProductFilter;
import com.bronze.boiler.filter.ProductReviewFilter;
import com.bronze.boiler.repository.*;
import com.bronze.boiler.util.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
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


}
