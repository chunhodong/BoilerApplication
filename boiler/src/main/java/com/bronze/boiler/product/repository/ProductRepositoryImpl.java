package com.bronze.boiler.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCst {
    private final JPAQueryFactory queryFactory;

}
