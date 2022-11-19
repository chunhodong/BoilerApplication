package com.bronze.boiler.domain.coupon;

import com.bronze.boiler.domain.base.BaseDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Builder
@Getter
@Entity
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class Stamp extends BaseDate {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private CouponWallet wallet;

    @Column
    private String name;
}
