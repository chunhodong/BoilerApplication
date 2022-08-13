package com.bronze.boiler.domain.product.entity;

import com.bronze.boiler.domain.base.BaseDate;
import com.bronze.boiler.domain.category.entity.Category;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.product.enums.ProductReviewStatus;
import com.bronze.boiler.domain.product.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.print.DocFlavor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
