package com.bronze.boiler.domain.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 1440859299L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final com.bronze.boiler.domain.base.QBaseDate _super = new com.bronze.boiler.domain.base.QBaseDate(this);

    public final com.bronze.boiler.domain.category.entity.QCategory category;

    public final StringPath code = createString("code");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final StringPath description = createString("description");

    public final BooleanPath hasOption = createBoolean("hasOption");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modified = _super.modified;

    public final StringPath name = createString("name");

    public final NumberPath<Long> originPrice = createNumber("originPrice", Long.class);

    public final StringPath refundInfo = createString("refundInfo");

    public final NumberPath<Long> savePoint = createNumber("savePoint", Long.class);

    public final StringPath sellerInfo = createString("sellerInfo");

    public final NumberPath<Long> sellPrice = createNumber("sellPrice", Long.class);

    public final StringPath sizeInfo = createString("sizeInfo");

    public final EnumPath<com.bronze.boiler.domain.product.enums.ProductStatus> status = createEnum("status", com.bronze.boiler.domain.product.enums.ProductStatus.class);

    public final QProductStock stock;

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.bronze.boiler.domain.category.entity.QCategory(forProperty("category"), inits.get("category")) : null;
        this.stock = inits.isInitialized("stock") ? new QProductStock(forProperty("stock"), inits.get("stock")) : null;
    }

}

