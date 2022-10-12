package com.bronze.boiler.domain.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Entity
@AllArgsConstructor
public class CouponWallet {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    @NotBlank(message = "이름을 입력해야합니다")
    private String name;

    @Column
    private Long level;
}
