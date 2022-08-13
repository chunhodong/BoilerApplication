package com.bronze.boiler.repository;

import com.bronze.boiler.domain.product.entity.Product;

import com.bronze.boiler.filter.ProductFilter;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import static com.bronze.boiler.domain.product.entity.QProduct.product;


@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCst{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Product> findAllByPage(ProductFilter productFilter) {


        BooleanBuilder builder = new BooleanBuilder();
        if(productFilter.getCategoryId() != null){
            builder.and(product.category.id.eq(productFilter.getCategoryId()));
        }
        if(productFilter.getStatus() != null){
            builder.and(product.status.eq(productFilter.getStatus()));
        }

        return queryFactory
                .selectFrom(product)
                .where(builder)
                .offset(productFilter.getPageOffset())
                .limit(productFilter.getPageSize())
                .fetch();
    }
}
