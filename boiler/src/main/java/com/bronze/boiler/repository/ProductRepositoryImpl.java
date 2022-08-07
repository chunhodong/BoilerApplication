package com.bronze.boiler.repository;

import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.filter.Page;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import java.util.List;
import static com.bronze.boiler.domain.product.entity.QProduct.product;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCst{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Product> findAllByPage(Page page) {

        return queryFactory
                .selectFrom(product)
                .offset(page.getPageOffset())
                .limit(page.getPageSize())
                .fetch();
    }
}
