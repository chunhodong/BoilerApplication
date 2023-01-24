package com.bronze.boiler.domain.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.bronze.boiler.review.domain.Review;
import com.bronze.boiler.review.domain.ReviewStatus;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = 785015259L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductReview productReview = new QProductReview("productReview");

    public final com.bronze.boiler.domain.base.QBaseDate _super = new com.bronze.boiler.domain.base.QBaseDate(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.bronze.boiler.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modified = _super.modified;

    public final QProductReview parent;

    public final QProduct product;

    public final EnumPath<ReviewStatus> status = createEnum("status", ReviewStatus.class);

    public final StringPath text = createString("text");

    public QProductReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QProductReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QProductReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.bronze.boiler.member.domain.QMember(forProperty("member")) : null;
        this.parent = inits.isInitialized("parent") ? new QProductReview(forProperty("parent"), inits.get("parent")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

