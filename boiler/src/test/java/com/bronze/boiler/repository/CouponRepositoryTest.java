package com.bronze.boiler.repository;


import com.bronze.boiler.config.TestConfig;
import com.bronze.boiler.domain.coupon.Coupon;
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
public class CouponRepositoryTest {


    @Autowired
    private CouponRepository couponRepository;


    @Test
    void 쿠폰조회(){
        //List<Coupon> coupons = couponRepository.findAllWithFetchJoin();
        boolean result = couponRepository.existsById(1l);


        System.out.println();
    }

}
