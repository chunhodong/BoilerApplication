package com.bronze.boiler.domain.gift;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGiftBoxWithSet is a Querydsl query type for GiftBoxWithSet
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGiftBoxWithSet extends EntityPathBase<GiftBoxWithSet> {

    private static final long serialVersionUID = -936307873L;

    public static final QGiftBoxWithSet giftBoxWithSet = new QGiftBoxWithSet("giftBoxWithSet");

    public final com.bronze.boiler.domain.base.QBaseDate _super = new com.bronze.boiler.domain.base.QBaseDate(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final SetPath<Gift, QGift> gifts = this.<Gift, QGift>createSet("gifts", Gift.class, QGift.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modified = _super.modified;

    public final StringPath name = createString("name");

    public final SetPath<Tag, QTag> tags = this.<Tag, QTag>createSet("tags", Tag.class, QTag.class, PathInits.DIRECT2);

    public QGiftBoxWithSet(String variable) {
        super(GiftBoxWithSet.class, forVariable(variable));
    }

    public QGiftBoxWithSet(Path<? extends GiftBoxWithSet> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGiftBoxWithSet(PathMetadata metadata) {
        super(GiftBoxWithSet.class, metadata);
    }

}

