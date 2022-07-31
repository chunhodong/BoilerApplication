package com.bronze.boiler.service;

import com.bronze.boiler.domain.member.converter.MemberConverter;
import com.bronze.boiler.domain.member.dto.ReqMemberDto;
import com.bronze.boiler.domain.member.dto.ResMemberDto;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.MemberExceptionType;
import com.bronze.boiler.domain.member.exception.MemberException;
import com.bronze.boiler.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
