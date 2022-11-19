package com.bronze.boiler.controller;

import com.bronze.boiler.domain.member.dto.ReqMemberDto;
import com.bronze.boiler.domain.member.dto.ResMemberDto;
import com.bronze.boiler.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<ResMemberDto> createMember(@RequestBody @Valid ReqMemberDto reqMemberDto) throws NoSuchAlgorithmException {
        ResMemberDto memberDto = memberService.createMember(reqMemberDto);
        return new ResponseEntity(memberDto,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResMemberDto> getMembers(@PathVariable("id") Long memberId) {
        ResMemberDto memberDto = memberService.getMember(memberId);
        return new ResponseEntity(memberDto,HttpStatus.OK);
    }

}
