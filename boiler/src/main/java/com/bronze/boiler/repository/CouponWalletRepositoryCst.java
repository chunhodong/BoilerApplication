package com.bronze.boiler.repository;

import com.bronze.boiler.domain.coupon.CouponWallet;

import java.util.List;

public interface CouponWalletRepositoryCst {

    List<CouponWallet> findAllWithFetchJoin();

    CouponWallet findOneWithFetchJoinAndWhere(Long walletId,Long couponId);
    CouponWallet findOneWithFetchJoin(Long walletId);

}
