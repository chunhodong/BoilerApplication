package com.bronze.boiler.repository;

import com.bronze.boiler.domain.member.entity.Member;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepositoryCst {
    List<Member> findAllByLastLogin(LocalDateTime startDate, LocalDateTime endDate, long limit);
}
