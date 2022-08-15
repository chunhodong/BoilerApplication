package com.bronze.boiler.domain.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Column(name = "title_address")
    @NotNull(message = "주소를 입력해야합니다")
    private String titleAddress;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column
    @NotNull(message = "우편번호를 입력해야합니다")
    private Long zipcode;
}
