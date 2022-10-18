package com.bronze.boiler.repository;

import com.bronze.boiler.domain.gift.GiftBox;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import static com.bronze.boiler.domain.gift.QGiftBox.giftBox;
import static com.bronze.boiler.domain.gift.QGift.gift;
import static com.bronze.boiler.domain.gift.QTag.tag;
@RequiredArgsConstructor
public class GiftBoxRepositoryImpl implements GiftBoxRepositoryCst {
    private final JPAQueryFactory queryFactory;


    @Override
    public List<GiftBox> findAllWithFetchJoin() {
        return queryFactory
                .selectFrom(giftBox)
                .join(giftBox.gifts, gift)
                .fetchJoin()
                .join(giftBox.tags, tag)
                .fetchJoin()
                .fetch();

    }
}
