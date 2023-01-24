package com.bronze.boiler.member.application;

import com.bronze.boiler.member.dto.MemberRequest;
import com.bronze.boiler.member.dto.MemberResponse;
import com.bronze.boiler.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse createMember(MemberRequest request) throws NoSuchAlgorithmException {
        return MemberResponse.of(memberRepository.save(request.toMember()));
    }
}

