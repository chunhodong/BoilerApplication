package com.bronze.boiler.domain.product.entity;

import com.bronze.boiler.domain.base.BaseDate;
import com.bronze.boiler.domain.product.enums.ProductStockExceptionType;
import com.bronze.boiler.exception.ProductStockException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class ProductStock extends BaseDate {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = false,unique = true)
    @NotNull(message = "상품을 입력해야합니다")
    private Product product;


    @Column(name = "current_stock")
    private Long currentStock;

    @Column(name = "total_stock")
    private Long totalStock;


    public void modifyCurrentStock(Long currentStock) {
        if(this.totalStock < currentStock){
            throw new ProductStockException(ProductStockExceptionType.ILLEGAL_STOCK);
        }
    }

    public void plusCurrentStock() {
        this.currentStock++;
    }

    public void minusCurrentStock() {
        this.currentStock--;
    }

    public boolean isRemainCurrentStock(){
        return this.currentStock > 0;
    }
}
