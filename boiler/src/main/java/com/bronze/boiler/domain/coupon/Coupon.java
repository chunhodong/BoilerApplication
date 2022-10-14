package com.bronze.boiler.domain.coupon;

import com.bronze.boiler.domain.base.BaseDate;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
/*    Object weo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private CouponWallet wallet;*/
/*
    @Column
    private String name;

    @Column
    private Long number;

    */

    @Column
    private Long number;


    @Convert(converter = PayConverter.class)
    private List<Pay> pays = new ArrayList<>();
/*
    public void changeName(String name){
        this.name = name;
    }*/
}
