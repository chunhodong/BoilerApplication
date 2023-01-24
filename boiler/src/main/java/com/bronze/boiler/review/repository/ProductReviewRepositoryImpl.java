package com.bronze.boiler.review.repository;

import com.bronze.boiler.review.domain.Review;
import com.bronze.boiler.domain.product.entity.QProductReview;
import com.bronze.boiler.review.ui.ReviewFilter;
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
import static com.bronze.boiler.domain.product.entity.QProduct.product;

@RequiredArgsConstructor
public class ProductReviewRepositoryImpl implements ProductReviewRepositoryCst {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> findAllByPage(ReviewFilter filter, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        if (filter.getParentId() == null) {
            builder.and(productReview.parent.isNull());
        } else builder.and(productReview.parent.id.eq(filter.getParentId()));
        if (filter.getStatus() != null) {
            builder.and(productReview.status.eq(filter.getStatus()));
        }
        return queryFactory
                .selectFrom(productReview)
                .where(builder)
                .orderBy(getOrderSpec(pageable.getSort(), productReview))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<Review> findAllWithFetchJoin() {
        return queryFactory
                .selectFrom(productReview)
                .leftJoin(productReview.product, product)
                .fetchJoin()
                .where(product.id.eq(1l))
                .fetch();
    }

    @Override
    public List<Review> findAllWithOffset(Long offset, Long limit) {
        return queryFactory
                .selectFrom(productReview)
                .orderBy(productReview.id.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Override
    public List<Review> findAllWithNoOffset(Long id, Long limit) {
        return queryFactory
                .selectFrom(productReview)
                .orderBy(productReview.id.desc())
                .where(productReview.id.loe(id))
                .limit(limit)
                .fetch();
    }

    public OrderSpecifier[] getOrderSpec(Sort sort, QProductReview productReview) {
        return sort.stream()
                .map(order -> new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC, new PathBuilder(productReview.getType(), productReview.getMetadata()).get(order.getProperty())))
                .collect(Collectors.toList())
                .toArray(OrderSpecifier[]::new);
    }
}
