package com.bronze.boiler.repository;

import com.bronze.boiler.domain.gift.GiftBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftBoxRepository extends JpaRepository<GiftBox,Long> {
}
