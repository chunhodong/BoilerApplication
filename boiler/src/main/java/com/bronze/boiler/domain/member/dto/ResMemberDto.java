package com.bronze.boiler.domain.member.dto;

import com.bronze.boiler.domain.member.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@Builder
@Getter
public class ResMemberDto {

    private Long id;

    private String name;

    private String email;

    private String password;

    private Role role;

}
