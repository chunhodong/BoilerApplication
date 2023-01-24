package com.bronze.boiler.member.application;

import com.bronze.boiler.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
}

