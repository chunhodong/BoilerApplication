package com.bronze.boiler.product.domain;

import com.bronze.boiler.common.BaseDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 상품이미지
 */
@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class ProductImage extends BaseDate {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String path;
    @Column
    private String name;
    @Column
    private String domain;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @NotNull(message = "상품을 추가해야합니다")
    private Product product;
}
