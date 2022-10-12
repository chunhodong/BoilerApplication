package com.bronze.boiler.repository;


import com.bronze.boiler.config.TestConfig;
import com.bronze.boiler.domain.coupon.Coupon;
import com.bronze.boiler.domain.coupon.CouponWallet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.stream.IntStream;

@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
@Rollback(value = false)
public class CouponRepositoryTest {


    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CouponWalletRepository couponWalletRepository;


    @Test
    @DisplayName("리파지토리")
    void 쿠폰추가() {

        IntStream.range(0, 10).forEach(value -> {

            couponRepository.save(Coupon.builder()
                    .name("쿠폰" + (value + 4))
                    .number(1103l + value + 2)
                    .build());

        });
    }

    @Test
    @DisplayName("리파지토리")
    void 쿠폰지갑추가() {

        IntStream.range(0, 5).forEach(value -> {

            couponWalletRepository.save(CouponWallet.builder()
                    .name("쿠폰지갑" + (value + 1))
                    .level((long) (value + 1))
                    .build());

        });
    }

}
