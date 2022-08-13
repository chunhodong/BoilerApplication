package com.bronze.boiler.domain.base;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseDate is a Querydsl query type for BaseDate
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseDate extends EntityPathBase<BaseDate> {

    private static final long serialVersionUID = -1766918980L;

    public static final QBaseDate baseDate = new QBaseDate("baseDate");

    public final DateTimePath<java.time.LocalDateTime> created = createDateTime("created", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> modified = createDateTime("modified", java.time.LocalDateTime.class);

    public QBaseDate(String variable) {
        super(BaseDate.class, forVariable(variable));
    }

    public QBaseDate(Path<? extends BaseDate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseDate(PathMetadata metadata) {
        super(BaseDate.class, metadata);
    }

}

