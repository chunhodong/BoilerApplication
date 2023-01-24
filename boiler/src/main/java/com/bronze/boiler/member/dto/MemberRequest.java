package com.bronze.boiler.member.dto;

import com.bronze.boiler.member.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class MemberRequest {
    private Long id;
    @NotBlank(message = "이름을 입력해야합니다")
    private String name;
    @NotBlank(message = "이메일을 입력해야합니다")
    private String email;
    @NotBlank(message = "비밀번호를 입력해야합니다")
    private String password;
    private Role role;
}

