package com.bronze.boiler.repository;

import com.bronze.boiler.domain.coupon.Coupon;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.bronze.boiler.domain.coupon.QCoupon.coupon;

@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepositoryCst {
    private final JPAQueryFactory queryFactory;


    @Override
    public List<Coupon> findAllWithFetchJoin() {
        return queryFactory
                .selectFrom(coupon)
                .fetch();
    }
}
