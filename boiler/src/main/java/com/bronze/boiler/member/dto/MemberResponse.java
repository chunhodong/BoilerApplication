package com.bronze.boiler.member.dto;

import com.bronze.boiler.member.domain.Member;
import com.bronze.boiler.member.domain.MemberStatus;
import com.bronze.boiler.member.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@AllArgsConstructor
@Builder
@Getter
public class MemberResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private MemberStatus status;
    private LocalDateTime created;

    private MemberResponse(Member member){
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.role = member.getRole();
        this.status = member.getStatus();
        this.status = member.getStatus();
        this.created = member.getCreated();
    }

    public static MemberResponse of(Member member) {
        return new MemberResponse(member);
    }
}
