package com.bronze.boiler.domain.member.dto;

import com.bronze.boiler.domain.member.enums.Role;
import com.bronze.boiler.domain.member.enums.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@AllArgsConstructor
@Builder
@Getter
public class ResMemberDto {

    private Long id;

    private String name;

    private String email;

    private String password;

    private Role role;

    private MemberStatus memberStatus;

    private LocalDate periodOfBlock;

    private LocalDateTime lastLogin;

}
