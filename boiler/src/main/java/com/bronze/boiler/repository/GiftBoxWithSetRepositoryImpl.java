package com.bronze.boiler.repository;

import com.bronze.boiler.domain.gift.GiftBoxWithSet;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.bronze.boiler.domain.gift.QGift.gift;
import static com.bronze.boiler.domain.gift.QGiftBoxWithSet.giftBoxWithSet;
import static com.bronze.boiler.domain.gift.QTag.tag;

@RequiredArgsConstructor
public class GiftBoxWithSetRepositoryImpl implements GiftBoxWithSetRepositoryCst {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<GiftBoxWithSet> findAllWithFetchJoin() {
        return queryFactory
                .selectFrom(giftBoxWithSet)
                .join(giftBoxWithSet.gifts, gift)
                .fetchJoin()
                .join(giftBoxWithSet.tags, tag)
                .fetchJoin()
                .fetch();
    }
}
