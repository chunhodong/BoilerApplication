package com.bronze.boiler.repository;


import com.bronze.boiler.config.TestConfig;
import com.bronze.boiler.domain.coupon.CouponWallet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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

    @Nested
    @DisplayName("1:N관계 엔티티에서 fetchJoin을 썻을때")
    class FetchJoinWithWhereClause{
        @Test
        @DisplayName("대상 엔티티에 where조건이 들어가면 일관성이 깨진다")
        void 쿠폰지갑조회_fetchJoin에서_where사용(){

            CouponWallet wallets1 = couponWalletRepository.findOneWithFetchJoinAndWhere(1l,10l);
            assertThat(wallets1.getCoupons().size()).isEqualTo(1l);

            //쿠폰사이즈는 3이 나와아 햐지만 먼저 조회한 where조건 떄문에 1가 나온다
            CouponWallet wallets2 = couponWalletRepository.findOneWithFetchJoin(1l);
            assertThat(wallets2.getCoupons().size()).isEqualTo(1l);
        }

        @Test
        @DisplayName("주인엔티티에 연관관계를 맺은 엔티티가 전부조회")
        void 쿠폰지갑조회_fetchJoin사용(){

            CouponWallet wallet = couponWalletRepository.findOneWithFetchJoin(1l);
            assertThat(wallet.getCoupons().size()).isEqualTo(3l);
        }
    }



}
