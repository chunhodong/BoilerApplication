package com.bronze.boiler.repository;

import com.bronze.boiler.domain.coupon.CouponWallet;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.bronze.boiler.domain.coupon.QCoupon.coupon;
import static com.bronze.boiler.domain.coupon.QCouponWallet.couponWallet;

@RequiredArgsConstructor
public class CouponWalletRepositoryImpl implements CouponWalletRepositoryCst {
    private final JPAQueryFactory queryFactory;


    @Override
    public List<CouponWallet> findAllWithFetchJoin() {
        return queryFactory
                .selectFrom(couponWallet)
                .join(couponWallet.coupons, coupon)
                .fetchJoin()
                .fetch();
    }

    @Override
    public CouponWallet findOneWithFetchJoinAndWhere(Long walletId,Long couponId) {
        return queryFactory
                .selectFrom(couponWallet)
                .join(couponWallet.coupons, coupon)
                .fetchJoin()
                .where(couponWallet.id.eq(walletId).and(coupon.id.eq(couponId)))
                .fetchOne();
    }

    @Override
    public CouponWallet findOneWithFetchJoin(Long walletId) {
        return queryFactory
                .selectFrom(couponWallet)
                .join(couponWallet.coupons, coupon)
                .fetchJoin()
                .where(couponWallet.id.eq(walletId))
                .fetchOne();
    }
}
