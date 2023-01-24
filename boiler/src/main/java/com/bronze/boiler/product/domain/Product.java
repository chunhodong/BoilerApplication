package com.bronze.boiler.product.domain;


import com.bronze.boiler.common.BaseDate;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Product extends BaseDate {
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
    private Long savePoint;
    @Column
    @NotBlank(message = "코드를 입력해야합니다")
    private String code;
    @Column
    @NotBlank(message = "상품설명을 입력해야합니다")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @ColumnDefault("'NEW'")
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @Column
    @NotBlank(message = "상품사이즈정보를 입력해야합니다")
    private String sizeInfo;
    @Column(name = "seller_info")
    @NotBlank(message = "판매자정보를 입력해야합니다")
    private String sellerInfo;
    @Column(name = "refund_info")
    @NotBlank(message = "배송/교환/환불 정보를 입력해야합니다")
    private String refundInfo;
    @Column(name = "has_option")
    private boolean hasOption;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }
}
