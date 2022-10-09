package com.bronze.boiler.repository;

import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.MemberStatus;
import com.bronze.boiler.domain.member.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long>,MemberRepositoryCst {


    Optional<Member> findByName(String name);


    Optional<Member> findByEmail(String email);

    long countByStatus(MemberStatus status);

    long countByRole(Role role);

    boolean existsByEmail(String email);

}
