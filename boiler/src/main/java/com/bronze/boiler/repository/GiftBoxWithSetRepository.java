package com.bronze.boiler.repository;

import com.bronze.boiler.domain.gift.GiftBoxWithSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftBoxWithSetRepository extends JpaRepository<GiftBoxWithSet,Long>,GiftBoxWithSetRepositoryCst {
}
