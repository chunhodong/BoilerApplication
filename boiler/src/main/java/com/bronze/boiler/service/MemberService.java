package com.bronze.boiler.service;

import com.bronze.boiler.domain.member.dto.MemberDto;
import com.bronze.boiler.domain.member.exception.DuplicateMemberException;
import com.bronze.boiler.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDto createMember(MemberDto memberDto) {

        memberRepository.findByName(memberDto.getName())
                .ifPresent(member -> {
                    throw new DuplicateMemberException();
                });
        return MemberDto.builder().build();
    }
}
