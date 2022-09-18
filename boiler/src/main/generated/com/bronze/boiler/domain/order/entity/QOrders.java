package com.bronze.boiler.domain.order.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrders is a Querydsl query type for Orders
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrders extends EntityPathBase<Orders> {

    private static final long serialVersionUID = 2012368080L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrders orders = new QOrders("orders");

    public final com.bronze.boiler.domain.base.QBaseDate _super = new com.bronze.boiler.domain.base.QBaseDate(this);

    public final QAddress address;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final NumberPath<Long> discountPrice = createNumber("discountPrice", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.bronze.boiler.domain.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modified = _super.modified;

    public final com.bronze.boiler.domain.payment.entity.QPayment payment;

    public final NumberPath<Long> paymentPrice = createNumber("paymentPrice", Long.class);

    public final EnumPath<com.bronze.boiler.domain.order.enums.OrderStatus> status = createEnum("status", com.bronze.boiler.domain.order.enums.OrderStatus.class);

    public final NumberPath<Long> totalPrice = createNumber("totalPrice", Long.class);

    public QOrders(String variable) {
        this(Orders.class, forVariable(variable), INITS);
    }

    public QOrders(Path<? extends Orders> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrders(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrders(PathMetadata metadata, PathInits inits) {
        this(Orders.class, metadata, inits);
    }

    public QOrders(Class<? extends Orders> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
        this.member = inits.isInitialized("member") ? new com.bronze.boiler.domain.member.entity.QMember(forProperty("member")) : null;
        this.payment = inits.isInitialized("payment") ? new com.bronze.boiler.domain.payment.entity.QPayment(forProperty("payment"), inits.get("payment")) : null;
    }

}

