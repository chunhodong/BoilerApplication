package com.bronze.boiler.domain.product.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 상품옵션
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductStockDto {


    private Long productId;

    @NotNull(message = "현재수량을 입력해야합니다")
    @Min(value = 0,message = "현재수량은 0이상이어야합니다")
    private Long currentStock;

    @NotNull(message = "전체수량을 입력해야합니다")
    @Min(value = 0,message = "전체수량은 0이상이어야합니다")
    private Long totalStock;


}
