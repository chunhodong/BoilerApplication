package com.bronze.boiler.domain.coupon;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStamp is a Querydsl query type for Stamp
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStamp extends EntityPathBase<Stamp> {

    private static final long serialVersionUID = 1018721243L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStamp stamp = new QStamp("stamp");

    public final com.bronze.boiler.domain.base.QBaseDate _super = new com.bronze.boiler.domain.base.QBaseDate(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modified = _super.modified;

    public final StringPath name = createString("name");

    public final QCouponWallet wallet;

    public QStamp(String variable) {
        this(Stamp.class, forVariable(variable), INITS);
    }

    public QStamp(Path<? extends Stamp> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStamp(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStamp(PathMetadata metadata, PathInits inits) {
        this(Stamp.class, metadata, inits);
    }

    public QStamp(Class<? extends Stamp> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.wallet = inits.isInitialized("wallet") ? new QCouponWallet(forProperty("wallet")) : null;
    }

}

