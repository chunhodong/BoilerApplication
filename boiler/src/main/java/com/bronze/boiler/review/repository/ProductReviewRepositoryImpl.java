package com.bronze.boiler.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductReviewRepositoryImpl implements ProductReviewRepositoryCst {
    private final JPAQueryFactory queryFactory;
}
