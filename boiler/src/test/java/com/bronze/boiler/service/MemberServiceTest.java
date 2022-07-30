package com.bronze.boiler.service;

import com.bronze.boiler.domain.member.dto.MemberDto;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Test
    void 회원추가_회원정보확인(){

        String name = "kim";
        String email = "test@test.com";
        String password = "password";
        Role role = Role.NORMAL;
        MemberDto memberDto = memberService.createMember(name,email,password,role);
        assertThat(memberDto).isNull();
    }


}
