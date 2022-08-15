package com.bronze.boiler.controller;

import com.bronze.boiler.domain.member.dto.ResMemberDto;
import com.bronze.boiler.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {




    private final MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<ResMemberDto> getMembers(@PathVariable("id") Long memberId) {
        ResMemberDto memberDto = memberService.getMember(memberId);
        return new ResponseEntity(memberDto,HttpStatus.OK);
    }
}
