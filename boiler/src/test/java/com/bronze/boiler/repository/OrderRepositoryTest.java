package com.bronze.boiler.repository;


import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.Role;
import com.bronze.boiler.domain.order.entity.Address;
import com.bronze.boiler.domain.order.entity.OrderProduct;
import com.bronze.boiler.domain.order.entity.Orders;
import com.bronze.boiler.domain.order.enums.OrderStatus;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.enums.ProductStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager em;

    @Test
    void 주문추가_필드NULL이면_예외발생() {
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            orderRepository.save(Orders.builder().build());
        });
        assertThat(exception).isNotNull();
    }

    @Test
    void 주문추가_주문확인() {
        Member member = memberRepository.save(Member.builder()
                .name("김딴딴")
                .email("test@test.com")
                .password("1234")
                .role(Role.USER).build());

        Orders order = orderRepository.save(Orders
                .builder()
                .paymentPrice(13000l)
                .totalPrice(13000l)
                .address(Address.builder().titleAddress("명일로172").zipcode(50320L).build())
                .status(OrderStatus.PAYMENT)
                .member(member)
                .build());
        assertThat(order).isNotNull();
    }

    @Test
    void 주문상품추가_필드NULL이면_예외발생() {
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class,
                () -> {
                    orderProductRepository.save(OrderProduct.builder().build());
                });
        assertThat(exception).isNotNull();
    }

    @Test
    void 주문상품추가_주문상품확인() {

        Member member = memberRepository.save(Member.builder()
                .name("김딴딴")
                .email("test@test.com")
                .password("1234")
                .role(Role.USER).build());
        Member savedMember = memberRepository.save(member);

        Orders order = orderRepository.save(Orders
                .builder()
                .paymentPrice(13000l)
                .totalPrice(13000l)
                .address(Address.builder().titleAddress("명일로172").zipcode(50320L).build())
                .status(OrderStatus.PAYMENT)
                .member(savedMember)
                .build());

        Product product = productRepository.save(Product.builder()
                .name("상품1")
                .code("AEOAK001")
                .description("상품설명")
                .status(ProductStatus.NEW)
                .originPrice(120000L)
                .refundInfo("환불정보")
                .sellerInfo("판매자정보")
                .sizeInfo("사이즈정보")
                .sellPrice(100000L)
                .savePoint(1000L)
                .sizeInfo("사이즈정보").build());
        OrderProduct orderProduct = orderProductRepository.save(OrderProduct.builder()
                .count(1l)
                .product(product)
                .order(order)
                .totalPrice(13000L).build());
        assertThat(orderProduct).isNotNull();
        assertThat(orderProduct.getProduct()).isEqualTo(product);
        assertThat(orderProduct.getOrder()).isEqualTo(order);

    }

    @Test
    void 주문상품추가_전체가격안맞으면_false() {

        Member member = memberRepository.save(Member.builder()
                .name("김딴딴")
                .email("test@test.com")
                .password("1234")
                .role(Role.USER).build());
        Member savedMember = memberRepository.save(member);

        Orders order = orderRepository.save(Orders
                .builder()
                .paymentPrice(13000l)
                .address(Address.builder().titleAddress("명일로172").zipcode(50320L).build())
                .totalPrice(13000l)
                .status(OrderStatus.PAYMENT)
                .member(savedMember)
                .build());

        Product product = productRepository.save(Product.builder()
                .name("상품1")
                .code("AEOAK001")
                .description("상품설명")
                .status(ProductStatus.NEW)
                .originPrice(120000L)
                .refundInfo("환불정보")
                .sellerInfo("판매자정보")
                .sizeInfo("사이즈정보")
                .sellPrice(100000L)
                .savePoint(1000L)
                .sizeInfo("사이즈정보").build());
        OrderProduct orderProduct = orderProductRepository.save(OrderProduct.builder()
                .count(1l)
                .product(product)
                .order(order)
                .totalPrice(1000200L).build());

        assertThat(orderProduct.isValidatePrice()).isEqualTo(false);

    }
}
