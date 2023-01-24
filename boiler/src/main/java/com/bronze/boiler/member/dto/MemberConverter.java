package com.bronze.boiler.member.dto;

import com.bronze.boiler.member.domain.Member;
import com.bronze.boiler.util.PasswordEncoder;

import java.security.NoSuchAlgorithmException;

public class MemberConverter {

    public static Member toMemberEntity(MemberRequest memberRequest) throws NoSuchAlgorithmException {
        return Member.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .password(PasswordEncoder.encrypt(memberRequest.getPassword()))
                .role(memberRequest.getRole())
                .build();

    }

    public static MemberResponse toMemberDto(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .role(member.getRole())
                .status(member.getStatus())
                .periodOfBlock(member.getPeriodOfBlock())
                .lastLogin(member.getLastLogin())
                .build();
    }
}
