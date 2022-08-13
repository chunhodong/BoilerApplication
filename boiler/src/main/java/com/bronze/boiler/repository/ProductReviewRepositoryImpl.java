package com.bronze.boiler.repository;

import com.bronze.boiler.domain.product.entity.ProductReview;
import com.bronze.boiler.domain.product.entity.QProductReview;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

import static com.bronze.boiler.domain.product.entity.QProductReview.productReview;


@RequiredArgsConstructor
public class ProductReviewRepositoryImpl implements ProductReviewRepositoryCst {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ProductReview> findAllByPage(Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        return queryFactory
                .selectFrom(productReview)
                .where(builder)
                .orderBy(getOrderSpec(pageable.getSort(),productReview))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public OrderSpecifier[] getOrderSpec(Sort sort, QProductReview productReview) {
        return sort.stream()
                .map(order -> new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC, new PathBuilder(productReview.getType(), productReview.getMetadata()).get(order.getProperty())))
                .collect(Collectors.toList())
                .toArray(OrderSpecifier[]::new);

    }

}
