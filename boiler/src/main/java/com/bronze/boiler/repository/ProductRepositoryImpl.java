package com.bronze.boiler.repository;

import com.bronze.boiler.domain.product.entity.Product;

import com.bronze.boiler.filter.ProductPage;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import static com.bronze.boiler.domain.product.entity.QProduct.product;


@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCst{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Product> findAllByPage(ProductPage productPage) {

        BooleanBuilder builder = new BooleanBuilder();
        if(productPage.getCategoryId() != null){
            builder.and(product.category.id.eq(productPage.getCategoryId()));
        }
        if(productPage.getStatus() != null){
            builder.and(product.status.eq(productPage.getStatus()));
        }

        return queryFactory
                .selectFrom(product)
                .where(builder)
                .offset(productPage.getPageOffset())
                .limit(productPage.getPageSize())
                .fetch();
    }
}
