package com.bronze.boiler.repository;

import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.Role;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.bronze.boiler.domain.member.entity.QMember.member;
import static com.bronze.boiler.domain.product.entity.QProductReview.productReview;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCst{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> findAllByLastLogin(LocalDateTime startDate,LocalDateTime endDate,long limit) {
        return queryFactory
                .selectFrom(member)
                .where(member.lastLogin.goe(startDate).and(member.lastLogin.loe(endDate)))
                .orderBy(member.lastLogin.desc())
                .limit(limit)
                .fetch();
    }

    @Override
    public List<Member> findAllWithSubquery(Long productReviewId) {
        return queryFactory
                .selectFrom(member)
                .where(member.id.in(JPAExpressions.select(productReview.member.id).from(productReview).where(productReview.id.lt(productReviewId))))
                .fetch();
    }

    @Override
    public List<Member> findAllWithDoubleQuery(Long productReviewId) {

        List<Long> memberIds =  queryFactory
                .select(productReview.member.id).distinct()
                .from(productReview)
                .where(productReview.id.lt(productReviewId))
                .fetch();
        return queryFactory
                .selectFrom(member)
                .where(member.id.in(memberIds))
                .fetch();
    }

    @Override
    public long updateMemberRoleInBatch(List<Long> memberIds, Role role) {
        return queryFactory
                .update(member)
                .set(member.role,role)
                .where(member.id.in(memberIds))
                .execute();
    }


}
