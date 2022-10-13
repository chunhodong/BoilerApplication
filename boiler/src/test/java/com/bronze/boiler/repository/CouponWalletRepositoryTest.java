package com.bronze.boiler.repository;


import com.bronze.boiler.config.TestConfig;
import com.bronze.boiler.domain.coupon.CouponWallet;
import org.hibernate.collection.internal.PersistentBag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
@Rollback(value = false)
public class CouponWalletRepositoryTest {


    @Autowired
    private CouponWalletRepository couponWalletRepository;

    PersistentBag aw;

    @Test
    void 쿠폰지갑조회(){
        List<CouponWallet> wallets = couponWalletRepository.findAll();
        /*List<Coupon> set = wallets.get(0).getCoupons();

        System.out.println();
        */
    }

}
