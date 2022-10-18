package com.bronze.boiler.repository;

import com.bronze.boiler.domain.gift.GiftBoxWithSet;

import java.util.List;

public interface GiftBoxWithSetRepositoryCst {

    List<GiftBoxWithSet> findAllWithFetchJoin();
}
