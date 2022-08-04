package com.bronze.boiler.domain.product.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 상품엔티티
 */
@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {



    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    @NotBlank(message = "이름을 입력해야합니다")
    private String name;

    @Column(name = "origin_price")
    @NotNull(message = "원가격을 입력해야합니다")
    private Long originPrice;

    @Column(name = "sell_price")
    @NotNull(message = "판매가를 입력해야합니다")
    private Long sellPrice;

    @Column
    private Long point;

    @Column
    @NotBlank(message = "코드를 입력해야합니다")
    private String code;

    @Column
    @NotBlank(message = "상품설명을 입력해야합니다")
    private String description;

    @Column
    @NotBlank(message = "상품사이즈정보를 입력해야합니다")
    private String sizeInfo;

    @Column(name = "seller_info")
    @NotBlank(message = "판매자정보를 입력해야합니다")
    private String sellerInfo;

    @Column(name = "refund_info")
    @NotBlank(message = "배송/교환/환불 정보를 입력해야합니다")
    private String refundInfo;



}
