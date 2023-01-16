package com.bronze.boiler.member.dto;

import com.bronze.boiler.member.domain.Member;
import com.bronze.boiler.util.PasswordEncoder;

import java.security.NoSuchAlgorithmException;

public class MemberConverter {

    public static Member toMemberEntity(ReqMemberDto reqMemberDto) throws NoSuchAlgorithmException {
        return Member.builder()
                .name(reqMemberDto.getName())
                .email(reqMemberDto.getEmail())
                .password(PasswordEncoder.encrypt(reqMemberDto.getPassword()))
                .role(reqMemberDto.getRole())
                .build();

    }

    public static ResMemberDto toMemberDto(Member member) {
        return ResMemberDto.builder()
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
