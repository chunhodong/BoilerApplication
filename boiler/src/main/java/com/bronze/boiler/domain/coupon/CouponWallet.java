package com.bronze.boiler.domain.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wallet")
    private List<Coupon> coupons;



}
