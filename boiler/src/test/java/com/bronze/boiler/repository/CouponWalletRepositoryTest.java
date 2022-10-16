package com.bronze.boiler.repository;


import com.bronze.boiler.config.TestConfig;
import com.bronze.boiler.domain.coupon.CouponWallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
@Rollback(value = false)
public class CouponWalletRepositoryTest {


    @Autowired
    private CouponWalletRepository couponWalletRepository;

    @Test
    void 쿠폰지갑조회_where사용(){
        CouponWallet wallets1 = couponWalletRepository.findOneWithFetchJoinAndWhere(1l,10l);
        assertThat(wallets1.getCoupons().size()).isEqualTo(1l);

        CouponWallet wallets2 = couponWalletRepository.findOneWithFetchJoin(1l);
        assertThat(wallets2.getCoupons().size()).isEqualTo(1l);
    }

    @Test
    void 쿠폰지갑조회_fetchJoin사용(){

        CouponWallet wallet = couponWalletRepository.findOneWithFetchJoin(1l);
        assertThat(wallet.getCoupons().size()).isEqualTo(3l);
    }

}
