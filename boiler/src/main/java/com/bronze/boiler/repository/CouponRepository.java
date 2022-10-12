package com.bronze.boiler.repository;

import com.bronze.boiler.domain.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long>,CouponRepositoryCst {
}
