package com.bronze.boiler.domain.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductStock is a Querydsl query type for ProductStock
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductStock extends EntityPathBase<ProductStock> {

    private static final long serialVersionUID = -1081692109L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductStock productStock = new QProductStock("productStock");

    public final com.bronze.boiler.domain.base.QBaseDate _super = new com.bronze.boiler.domain.base.QBaseDate(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final NumberPath<Long> currentStock = createNumber("currentStock", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> inStock = createNumber("inStock", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modified = _super.modified;

    public final QProduct product;

    public final QProductOption productOption;

    public QProductStock(String variable) {
        this(ProductStock.class, forVariable(variable), INITS);
    }

    public QProductStock(Path<? extends ProductStock> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductStock(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductStock(PathMetadata metadata, PathInits inits) {
        this(ProductStock.class, metadata, inits);
    }

    public QProductStock(Class<? extends ProductStock> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
        this.productOption = inits.isInitialized("productOption") ? new QProductOption(forProperty("productOption"), inits.get("productOption")) : null;
    }

}

