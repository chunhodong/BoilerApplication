package com.bronze.boiler.repository;

import com.bronze.boiler.domain.coupon.CouponWallet;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.bronze.boiler.domain.coupon.QCoupon.coupon;
import static com.bronze.boiler.domain.coupon.QCouponWallet.couponWallet;
import static com.bronze.boiler.domain.coupon.QStamp.stamp;

@RequiredArgsConstructor
public class CouponWalletRepositoryImpl implements CouponWalletRepositoryCst {
    private final JPAQueryFactory queryFactory;


    @Override
    public List<CouponWallet> findAllWithFetchJoin() {
        return queryFactory
                .selectFrom(couponWallet)
                .join(couponWallet.coupons, coupon)
                .fetchJoin()
                .join(couponWallet.stamps, stamp)
                .fetchJoin()
                .fetch();
    }
}
