package com.bronze.boiler.service;

import com.bronze.boiler.domain.member.converter.MemberConverter;
import com.bronze.boiler.domain.member.dto.ReqMemberDto;
import com.bronze.boiler.domain.member.dto.ResMemberDto;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.MemberExceptionType;
import com.bronze.boiler.exception.MemberException;
import com.bronze.boiler.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.security.NoSuchAlgorithmException;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     *
     * @param reqMemberDto 회원정보DtO
     * @return 회원정보DTO
     * @throws NoSuchAlgorithmException 비밀번호암호화 알고리즘 검색실패
     */
    public ResMemberDto createMember(ReqMemberDto reqMemberDto) throws NoSuchAlgorithmException {
        memberRepository.findByName(reqMemberDto.getName())
                .ifPresent(member -> {
                    throw new MemberException(MemberExceptionType.DUPLICATE_NAME);
                });
        memberRepository.findByEmail(reqMemberDto.getEmail())
                .ifPresent(member -> {
                    throw new MemberException(MemberExceptionType.DUPLICATE_EMAIL);
                });
        Member member = memberRepository.save(MemberConverter.toMemberEntity(reqMemberDto));
        return MemberConverter.toMemberDto(member);
    }

    /**
     * 회원삭제처리
     * @param memberId 회원아이디
     * @return 삭제처리된회원정보
     */
    public ResMemberDto removeMember(long memberId) {
        Member member = memberRepository.findById(memberId)
                        .orElseThrow(() -> new MemberException(MemberExceptionType.NONE_EXIST_MEMBER));
        member.remove();
        return MemberConverter.toMemberDto(member);
    }

    /**
     * 회원탈퇴처리
     * @param memberId 회원아이디
     * @return 탈퇴처리된회원정보
     */
    public ResMemberDto unregisterMember(long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NONE_EXIST_MEMBER));
        member.unregister();
        return MemberConverter.toMemberDto(member);

    }

    /**
     * 회원휴면처리
     * @param memberId 회원아이디
     * @return 탈퇴처리된회원정보
     */
    public ResMemberDto sleepMember(long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NONE_EXIST_MEMBER));
        member.sleep();
        return MemberConverter.toMemberDto(member);

    }

    /**
     * 회원정지처리
     * @param memberId 회원아이디
     * @return 정지처리된회원정보
     */
    public ResMemberDto blockMember(long memberId) {
        ExceptionHandlerExceptionResolver ae;
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NONE_EXIST_MEMBER));
        member.block();
        return MemberConverter.toMemberDto(member);

    }

    /**
     * 마지막로그인시간갱신
     * @param memberId 회원아이디
     * @return 갱신처리된회원정보
     */
    public ResMemberDto modifyLastlogin(long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NONE_EXIST_MEMBER));
        member.renewLastLogin();
        return MemberConverter.toMemberDto(member);

    }

    /**
     * 회원조회
     * @param memberId 회원아이디
     * @return 회원데이터
     */
    public ResMemberDto getMember(long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NONE_EXIST_MEMBER));
        return MemberConverter.toMemberDto(member);

    }
}

