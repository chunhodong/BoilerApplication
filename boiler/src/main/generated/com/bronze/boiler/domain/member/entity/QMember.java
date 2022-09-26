package com.bronze.boiler.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -292363567L;

    public static final QMember member = new QMember("member1");

    public final com.bronze.boiler.domain.base.QBaseDate _super = new com.bronze.boiler.domain.base.QBaseDate(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> lastLogin = createDateTime("lastLogin", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modified = _super.modified;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final DatePath<java.time.LocalDate> periodOfBlock = createDate("periodOfBlock", java.time.LocalDate.class);

    public final EnumPath<com.bronze.boiler.domain.member.enums.Role> role = createEnum("role", com.bronze.boiler.domain.member.enums.Role.class);

    public final EnumPath<com.bronze.boiler.domain.member.enums.MemberStatus> status = createEnum("status", com.bronze.boiler.domain.member.enums.MemberStatus.class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

