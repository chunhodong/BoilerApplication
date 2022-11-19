package com.bronze.boiler.repository;

import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.QProduct;
import com.bronze.boiler.filter.ProductFilter;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

import static com.bronze.boiler.domain.product.entity.QProduct.product;


@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCst{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Product> findAllByPage(ProductFilter productFilter, Pageable pageable) {
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
                .orderBy(getOrderSpec(pageable.getSort(),product))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<Product> findAllMaxPrice() {
        return queryFactory
                .selectFrom(product)
                .where(product.sellPrice.eq(JPAExpressions.select(product.sellPrice.max()).from(product)))
                .fetch();

    }

    public OrderSpecifier[] getOrderSpec(Sort sort, QProduct product) {
        return sort.stream()
                .map(order -> new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC, new PathBuilder(product.getType(), product.getMetadata()).get(order.getProperty())))
                .collect(Collectors.toList())
                .toArray(OrderSpecifier[]::new);
    }
}
