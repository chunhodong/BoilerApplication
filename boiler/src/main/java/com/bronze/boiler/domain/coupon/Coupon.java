package com.bronze.boiler.domain.coupon;

import com.bronze.boiler.domain.base.BaseDate;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@EqualsAndHashCode
@Builder
@Getter
@Entity
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class Coupon extends BaseDate {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "wallet_id")
    private CouponWallet wallet;

    @Column
    private String name;

    @Column
    private Long number;

}
