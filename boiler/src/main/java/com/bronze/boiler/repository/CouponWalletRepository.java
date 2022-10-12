package com.bronze.boiler.repository;

import com.bronze.boiler.domain.coupon.CouponWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponWalletRepository extends JpaRepository<CouponWallet,Long>,CouponWalletRepositoryCst {
}
