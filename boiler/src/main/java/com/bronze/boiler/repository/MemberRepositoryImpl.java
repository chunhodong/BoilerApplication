package com.bronze.boiler.repository;

import com.bronze.boiler.domain.member.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.bronze.boiler.domain.member.entity.QMember.member;

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




}
