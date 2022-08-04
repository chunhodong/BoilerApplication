package com.bronze.boiler.domain.product.entity;


import com.bronze.boiler.domain.product.enums.OptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 상품옵션
 */
@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProductOption {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column
    @NotNull(message = "옵션타입을 입력하세요")
    @Enumerated(EnumType.STRING)
    private OptionType type;

    @Column
    @NotNull(message = "옵션값을 입력하세요")
    private Long value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @NotNull(message = "상품을 추가해야합니다")
    private Product product;


}
