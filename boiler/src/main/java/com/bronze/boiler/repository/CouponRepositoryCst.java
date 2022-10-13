package com.bronze.boiler.repository;

import com.bronze.boiler.domain.coupon.Coupon;

import java.util.List;

public interface CouponRepositoryCst {

    List<Coupon> findAllWithFetchJoin();


}
