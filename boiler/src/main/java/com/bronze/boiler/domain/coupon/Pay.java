package com.bronze.boiler.domain.coupon;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Pay {

    private String code;
    private Long amount;
    private List<PayDetail> details = new ArrayList<>();

    public Pay(String code, Long amount, PayDetail detail) {
        this.code = code;
        this.amount = amount;
        this.details.add(detail);
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PayDetail {
        private String salesType;
        private Long amount;
    }
}
