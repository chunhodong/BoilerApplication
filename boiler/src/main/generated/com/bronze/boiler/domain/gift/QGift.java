package com.bronze.boiler.domain.gift;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGift is a Querydsl query type for Gift
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGift extends EntityPathBase<Gift> {

    private static final long serialVersionUID = -2068718770L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGift gift = new QGift("gift");

    public final com.bronze.boiler.domain.base.QBaseDate _super = new com.bronze.boiler.domain.base.QBaseDate(this);

    public final QGiftBox box;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modified = _super.modified;

    public final StringPath name = createString("name");

    public final NumberPath<Long> quantity = createNumber("quantity", Long.class);

    public QGift(String variable) {
        this(Gift.class, forVariable(variable), INITS);
    }

    public QGift(Path<? extends Gift> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGift(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGift(PathMetadata metadata, PathInits inits) {
        this(Gift.class, metadata, inits);
    }

    public QGift(Class<? extends Gift> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.box = inits.isInitialized("box") ? new QGiftBox(forProperty("box")) : null;
    }

}

