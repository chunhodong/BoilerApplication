package com.bronze.boiler.repository;

import com.bronze.boiler.domain.member.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {


    Optional<Member> findByName(String name);


    Optional<Member> findByEmail(String email);
}
