package com.bronze.boiler.domain.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductQna is a Querydsl query type for ProductQna
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductQna extends EntityPathBase<ProductQna> {

    private static final long serialVersionUID = 736301633L;

    public static final QProductQna productQna = new QProductQna("productQna");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QProductQna(String variable) {
        super(ProductQna.class, forVariable(variable));
    }

    public QProductQna(Path<? extends ProductQna> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductQna(PathMetadata metadata) {
        super(ProductQna.class, metadata);
    }

}

