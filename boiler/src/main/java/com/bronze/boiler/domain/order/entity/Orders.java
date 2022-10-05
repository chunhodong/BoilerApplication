package com.bronze.boiler.domain.order.entity;

import com.bronze.boiler.domain.base.BaseDate;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.order.enums.OrderStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Orders extends BaseDate {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "주문상태를 입력해야합니다")
    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatus status;

    @NotNull(message = "총금액을 입력해야합니다")
    private Long totalPrice;

    private Long discountPrice;

    @NotNull(message = "판매금액을 입력해야합니다")
    private Long paymentPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull(message = "회원이 있어야합니다")
    private Member member;

    @Embedded
    @NotNull
    private Address address;


    public void cancel() {
        this.status = OrderStatus.CANCEL;
    }

    public void refund() {
        this.status = OrderStatus.REFUND;
    }

    public void modifyAddress(Address address) {
        this.address = address;
    }
}
