package com.bronze.boiler.repository;

import com.bronze.boiler.domain.gift.GiftBox;

import java.util.List;

public interface GiftBoxRepositoryCst {

    List<GiftBox> findAllWithFetchJoin();
}
