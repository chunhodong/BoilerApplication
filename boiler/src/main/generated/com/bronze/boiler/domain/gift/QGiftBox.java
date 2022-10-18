package com.bronze.boiler.domain.gift;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGiftBox is a Querydsl query type for GiftBox
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGiftBox extends EntityPathBase<GiftBox> {

    private static final long serialVersionUID = -715079779L;

    public static final QGiftBox giftBox = new QGiftBox("giftBox");

    public final com.bronze.boiler.domain.base.QBaseDate _super = new com.bronze.boiler.domain.base.QBaseDate(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final ListPath<Gift, QGift> gifts = this.<Gift, QGift>createList("gifts", Gift.class, QGift.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modified = _super.modified;

    public final StringPath name = createString("name");

    public final ListPath<Tag, QTag> tags = this.<Tag, QTag>createList("tags", Tag.class, QTag.class, PathInits.DIRECT2);

    public QGiftBox(String variable) {
        super(GiftBox.class, forVariable(variable));
    }

    public QGiftBox(Path<? extends GiftBox> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGiftBox(PathMetadata metadata) {
        super(GiftBox.class, metadata);
    }

}

