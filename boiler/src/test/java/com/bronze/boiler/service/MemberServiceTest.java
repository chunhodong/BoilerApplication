package com.bronze.boiler.service;

import com.bronze.boiler.domain.member.dto.ReqMemberDto;
import com.bronze.boiler.domain.member.dto.ResMemberDto;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.MemberExceptionType;
import com.bronze.boiler.domain.member.enums.Role;
import com.bronze.boiler.domain.member.enums.MemberStatus;
import com.bronze.boiler.exception.MemberException;
import com.bronze.boiler.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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
    //스프링컨테이너와 독립적으로 Mock객체를 생성
    @Mock
    private MemberRepository memberRepository;

    private final ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);


    @Test
    void 회원추가_이미존재하는이름_예외발생() {
        ReqMemberDto reqMemberDto = ReqMemberDto.builder()
                .name("김딱딱")
                .email("email@email.com")
                .password("123")
                .role(Role.USER)
                .build();
        doReturn(Optional.ofNullable(Member.builder().build()))
                .when(memberRepository).findByName(any());
        MemberException exception = assertThrows(MemberException.class, () -> memberService.createMember(reqMemberDto));
        assertThat(exception.getType().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(exception.getType()).isEqualTo(MemberExceptionType.DUPLICATE_NAME);


    }

    @Test
    void 회원추가_이미존재하는이메일_예외발생() {
        ReqMemberDto reqMemberDto = ReqMemberDto.builder()
                .name("김딱딱")
                .email("email@email.com")
                .password("123")
                .role(Role.USER)
                .build();
        doReturn(Optional.ofNullable(Member.builder().build()))
                .when(memberRepository).findByEmail(any());
        MemberException exception = assertThrows(MemberException.class, () -> memberService.createMember(reqMemberDto));
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
        assertThat(captor.getValue().getPassword()).isNotEqualTo(reqMemberDto.getPassword());


    }


    @Test
    void 회원추가_회원정보확인() throws NoSuchAlgorithmException {


        ReqMemberDto reqMemberDto = ReqMemberDto.builder()
                .name("테스트유저")
                .email("email@email")
                .password("1234")
                .role(Role.USER)
                .build();


        doReturn(Member.builder()
                .name("테스트유저")
                .email("email@email")
                .role(Role.USER)
                .build())
                .when(memberRepository).save(any());

        ResMemberDto resMemberDto = memberService.createMember(reqMemberDto);
        assertThat(resMemberDto.getEmail()).isEqualTo(reqMemberDto.getEmail());
        assertThat(resMemberDto.getName()).isEqualTo(reqMemberDto.getName());
        assertThat(resMemberDto.getRole()).isEqualTo(reqMemberDto.getRole());
    }

    @Test
    void 회원삭제_없는회원조회_예외발생() {

        doReturn(Optional.empty())
                .when(memberRepository).findById(any());

        MemberException memberException = assertThrows(MemberException.class, () -> memberService.removeMember(12L));
        assertThat(memberException.getType()).isEqualTo(MemberExceptionType.NONE_EXIST_MEMBER);
    }


    @Test
    void 회원삭제_회원정보확인() {

        doReturn(Optional.ofNullable(Member.builder()
                .name("테스트유저")
                .email("email@email")
                .role(Role.USER)
                .status(MemberStatus.NORMAL)
                .build()))
                .when(memberRepository).findById(any());

        ResMemberDto resMemberDto = memberService.removeMember(12L);
        assertThat(resMemberDto.getStatus()).isEqualTo(MemberStatus.REMOVE);
    }

    @Test
    void 회원탈퇴_없는회원조회_예외발생() {

        doReturn(Optional.empty())
                .when(memberRepository).findById(any());

        MemberException memberException = assertThrows(MemberException.class, () -> memberService.unregisterMember(12L));
        assertThat(memberException.getType()).isEqualTo(MemberExceptionType.NONE_EXIST_MEMBER);
    }

    @Test
    void 회원탈퇴_회원정보확인() {

        doReturn(Optional.ofNullable(Member.builder()
                .name("테스트유저")
                .email("email@email")
                .role(Role.USER)
                .status(MemberStatus.NORMAL)
                .build()))
                .when(memberRepository).findById(any());

        ResMemberDto resMemberDto = memberService.unregisterMember(12L);
        assertThat(resMemberDto.getStatus()).isEqualTo(MemberStatus.UNREGISTER);
    }


    @Test
    void 회원정지_없는회원조회_예외발생() {

        doReturn(Optional.empty())
                .when(memberRepository).findById(any());

        MemberException memberException = assertThrows(MemberException.class, () -> memberService.blockMember(12L));
        assertThat(memberException.getType()).isEqualTo(MemberExceptionType.NONE_EXIST_MEMBER);
    }


    @Test
    void 회원정지_정지날짜확인() {

        doReturn(Optional.ofNullable(Member.builder()
                .name("테스트유저")
                .email("email@email")
                .role(Role.USER)
                .status(MemberStatus.NORMAL)
                .build()))
                .when(memberRepository).findById(any());

        ResMemberDto resMemberDto = memberService.blockMember(12L);
        assertThat(resMemberDto.getPeriodOfBlock()).isEqualTo(LocalDate.now().plusDays(7));

    }

    @Test
    void 회원휴면_휴면상태확인() {

        doReturn(Optional.ofNullable(Member.builder()
                .name("테스트유저")
                .email("email@email")
                .role(Role.USER)
                .status(MemberStatus.NORMAL)
                .build()))
                .when(memberRepository).findById(any());

        ResMemberDto resMemberDto = memberService.sleepMember(12L);
        assertThat(resMemberDto.getStatus()).isEqualTo(MemberStatus.SLEEP);

    }


    @Test
    void 회원마지막로그인갱신_마지막로그인시간확인() {

        doReturn(Optional.ofNullable(Member.builder()
                .name("테스트유저")
                .email("email@email")
                .role(Role.USER)
                .status(MemberStatus.NORMAL)
                .build()))
                .when(memberRepository).findById(any());

        ResMemberDto resMemberDto = memberService.modifyLastlogin(12L);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        assertThat(resMemberDto.getLastLogin().format(dtf)).isEqualTo(LocalDateTime.now().format(dtf));

    }

    @Test
    void 회원조회_회원데이터확인() {

        doReturn(Optional.ofNullable(Member.builder()
                .id(13L)
                .name("테스트유저")
                .email("email@email")
                .role(Role.USER)
                .status(MemberStatus.NORMAL)
                .build()))
                .when(memberRepository).findById(any());

        ResMemberDto memberDto = memberService.getMember(13L);

        assertThat(memberDto.getId()).isEqualTo(13L);
        assertThat(memberDto.getName()).isEqualTo("테스트유저");
        assertThat(memberDto.getEmail()).isEqualTo("email@email");


    }


}
