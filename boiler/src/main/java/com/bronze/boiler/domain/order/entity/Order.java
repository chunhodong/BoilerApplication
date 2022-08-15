package com.bronze.boiler.domain.order.entity;

import com.bronze.boiler.domain.base.BaseDate;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.order.enums.OrderStatus;
import com.bronze.boiler.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseDate {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OrderStatus status;

    private Long totalPrice;

    private Long discountPrice;

    private Long paymentPrice;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull(message = "회원이 있어야합니다")
    private Member member;
}
