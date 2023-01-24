package com.bronze.boiler.domain.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.bronze.boiler.product.domain.ProductImage;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductImage is a Querydsl query type for ProductImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductImage extends EntityPathBase<ProductImage> {

    private static final long serialVersionUID = -1091149192L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductImage productImage = new QProductImage("productImage");

    public final com.bronze.boiler.domain.base.QBaseDate _super = new com.bronze.boiler.domain.base.QBaseDate(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final StringPath domain = createString("domain");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modified = _super.modified;

    public final StringPath name = createString("name");

    public final StringPath path = createString("path");

    public final QProduct product;

    public QProductImage(String variable) {
        this(ProductImage.class, forVariable(variable), INITS);
    }

    public QProductImage(Path<? extends ProductImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductImage(PathMetadata metadata, PathInits inits) {
        this(ProductImage.class, metadata, inits);
    }

    public QProductImage(Class<? extends ProductImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

