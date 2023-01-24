package com.bronze.boiler.coupon.domain;

import com.bronze.boiler.common.BaseDate;
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
    @Column
    private String name;
    @Column
    private Long number;
}
