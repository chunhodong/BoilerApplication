package com.bronze.boiler.service;

import com.bronze.boiler.domain.member.dto.ReqMemberDto;
import com.bronze.boiler.domain.member.dto.ResMemberDto;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.MemberExceptionType;
import com.bronze.boiler.domain.member.enums.Role;
import com.bronze.boiler.domain.member.exception.MemberException;
import com.bronze.boiler.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

//테스트실행 확장을 위해 추가하는 Annotation
@ExtendWith(SpringExtension.class)
public class MemberServiceTest {


    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    private final ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);


    @Test
    void 회원추가_이미존재하는이름_예외발생(){
        ReqMemberDto reqMemberDto = ReqMemberDto.builder()
                .name("김딱딱")
                .email("email@email.com")
                .password("123")
                .role(Role.NORMAL)
                .build();
        doReturn(Optional.ofNullable(Member.builder().build()))
                .when(memberRepository).findByName(any());
        MemberException exception = assertThrows(MemberException.class,() -> memberService.createMember(reqMemberDto));
        assertThat(exception.getType().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(exception.getType()).isEqualTo(MemberExceptionType.DUPLICATE_NAME);


    }

    @Test
    void 회원추가_이미존재하는이메일_예외발생(){
        ReqMemberDto reqMemberDto = ReqMemberDto.builder()
                .name("김딱딱")
                .email("email@email.com")
                .password("123")
                .role(Role.NORMAL)
                .build();
        doReturn(Optional.ofNullable(Member.builder().build()))
                .when(memberRepository).findByEmail(any());
        MemberException exception = assertThrows(MemberException.class,() -> memberService.createMember(reqMemberDto));
        assertThat(exception.getType().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(exception.getType()).isEqualTo(MemberExceptionType.DUPLICATE_EMAIL);




    }


    @Test
    void 회원추가_비밀번호암호화없으면_예외발생() throws NoSuchAlgorithmException {

        ReqMemberDto reqMemberDto = ReqMemberDto.builder().password("awfawewaf").build();

        doReturn(Member.builder().build())
                .when(memberRepository).save(any());

        memberService.createMember(reqMemberDto);
        verify(memberRepository).save(captor.capture());
        System.out.println("cc : "+captor.getValue().getPassword());
        assertThat(captor.getValue().getPassword()).isNotEqualTo(reqMemberDto.getPassword());


    }




    @Test
    void 회원추가_회원정보확인() throws NoSuchAlgorithmException {


        ReqMemberDto reqMemberDto = ReqMemberDto.builder()
                .name("테스트유저")
                .email("email@email")
                .password("1234")
                .role(Role.NORMAL)
                .build();


        doReturn(Member.builder()
                .name("테스트유저")
                .email("email@email")
                .role(Role.NORMAL)
                .build())
                .when(memberRepository).save(any());

        ResMemberDto resMemberDto = memberService.createMember(reqMemberDto);
        assertThat(resMemberDto.getEmail()).isEqualTo(reqMemberDto.getEmail());
        assertThat(resMemberDto.getName()).isEqualTo(reqMemberDto.getName());
        assertThat(resMemberDto.getRole()).isEqualTo(reqMemberDto.getRole());
    }




}
