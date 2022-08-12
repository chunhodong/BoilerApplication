package com.bronze.boiler.domain.product.dto;


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
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionDto {

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
    private String value;



}
