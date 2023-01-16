package com.bronze.boiler.domain.product.entity;

import com.bronze.boiler.domain.base.BaseDate;
import com.bronze.boiler.member.domain.Member;
import com.bronze.boiler.domain.product.enums.ProductReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class ProductReview extends BaseDate {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull(message = "작성자를 입력해야합니다")
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @NotNull(message = "상품을 입력해야합니다")
    private Product product;


    @NotBlank(message = "내용을 입력해야합니다")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ProductReview parent;



    @ColumnDefault("'NEW'")
    @Enumerated(EnumType.STRING)
    private ProductReviewStatus status;

    public boolean isRemoved() {
        return status == ProductReviewStatus.REMOVED;
    }
}
