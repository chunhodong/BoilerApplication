package com.bronze.boiler.domain.coupon;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCouponWallet is a Querydsl query type for CouponWallet
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCouponWallet extends EntityPathBase<CouponWallet> {

    private static final long serialVersionUID = -451581785L;

    public static final QCouponWallet couponWallet = new QCouponWallet("couponWallet");

    public final ListPath<Coupon, QCoupon> coupons = this.<Coupon, QCoupon>createList("coupons", Coupon.class, QCoupon.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> level = createNumber("level", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<Stamp, QStamp> stamps = this.<Stamp, QStamp>createList("stamps", Stamp.class, QStamp.class, PathInits.DIRECT2);

    public QCouponWallet(String variable) {
        super(CouponWallet.class, forVariable(variable));
    }

    public QCouponWallet(Path<? extends CouponWallet> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCouponWallet(PathMetadata metadata) {
        super(CouponWallet.class, metadata);
    }

}

