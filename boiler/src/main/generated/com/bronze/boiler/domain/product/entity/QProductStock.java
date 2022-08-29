package com.bronze.boiler.domain.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductStock is a Querydsl query type for ProductStock
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductStock extends EntityPathBase<ProductStock> {

    private static final long serialVersionUID = -1081692109L;

    public static final QProductStock productStock = new QProductStock("productStock");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QProductStock(String variable) {
        super(ProductStock.class, forVariable(variable));
    }

    public QProductStock(Path<? extends ProductStock> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductStock(PathMetadata metadata) {
        super(ProductStock.class, metadata);
    }

}

