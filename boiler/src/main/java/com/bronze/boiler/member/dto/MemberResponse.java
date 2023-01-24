package com.bronze.boiler.member.dto;

import com.bronze.boiler.member.domain.Role;
import com.bronze.boiler.member.domain.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
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

    private LocalDate periodOfBlock;

    private LocalDateTime lastLogin;

}
