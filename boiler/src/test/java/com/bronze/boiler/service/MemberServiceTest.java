package com.bronze.boiler.service;

import com.bronze.boiler.domain.member.dto.MemberDto;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.MemberExceptionType;
import com.bronze.boiler.domain.member.enums.Role;
import com.bronze.boiler.domain.member.exception.DuplicateMemberException;
import com.bronze.boiler.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;


    @Test
    void 회원추가_이미존재하는이름_예외발생(){
        MemberDto memberDto = MemberDto.builder()
                .name("김딱딱")
                .email("email@email.com")
                .password("123")
                .role(Role.NORMAL)
                .build();
        doReturn(Optional.ofNullable(Member.builder().build()))
                .when(memberRepository).findByName(any());
        DuplicateMemberException exception = assertThrows(DuplicateMemberException.class,() -> memberService.createMember(memberDto));
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(exception.getType()).isEqualTo(MemberExceptionType.DUPLICATE_NAME);


    }

    @Test
    void 회원추가_이미존재하는이메일_예외발생(){
        MemberDto memberDto = MemberDto.builder()
                .name("김딱딱")
                .email("email@email.com")
                .password("123")
                .role(Role.NORMAL)
                .build();
        doReturn(Optional.ofNullable(Member.builder().build()))
                .when(memberRepository).findByEmail(any());
        DuplicateMemberException exception = assertThrows(DuplicateMemberException.class,() -> memberService.createMember(memberDto));
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(exception.getType()).isEqualTo(MemberExceptionType.DUPLICATE_EMAIL);




    }


    @Test
    void 회원추가_비밀번호암호화없으면_예외발생(){

    }


}
