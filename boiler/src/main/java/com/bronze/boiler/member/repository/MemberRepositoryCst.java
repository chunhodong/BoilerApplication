package com.bronze.boiler.member.repository;

import com.bronze.boiler.member.domain.Member;
import com.bronze.boiler.member.domain.Role;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepositoryCst {
    List<Member> findAllByLastLogin(LocalDateTime startDate, LocalDateTime endDate, long limit);
    List<Member> findAllWithSubquery(Long productReviewId);
    List<Member> findAllWithDoubleQuery(Long productReviewId);
    long updateMemberRoleInBatch(List<Long> memberIds, Role role);
}
