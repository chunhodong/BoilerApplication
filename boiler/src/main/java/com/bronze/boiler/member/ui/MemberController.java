package com.bronze.boiler.member.ui;

import com.bronze.boiler.member.application.MemberService;
import com.bronze.boiler.member.dto.MemberRequest;
import com.bronze.boiler.member.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@RequestBody MemberRequest memberRequest) throws NoSuchAlgorithmException {
        MemberResponse member = memberService.createMember(memberRequest);
        return new ResponseEntity(member,HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> getMembers(@PathVariable("memberId") Long memberId) {
        MemberResponse member = memberService.getMember(memberId);
        return new ResponseEntity(member,HttpStatus.OK);
    }

}
