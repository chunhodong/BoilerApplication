package com.bronze.boiler.repository;

import com.bronze.boiler.domain.gift.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftRepository extends JpaRepository<Gift,Long> {
}
