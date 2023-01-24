package com.bronze.boiler.member.ui;

import com.bronze.boiler.member.application.MemberService;
import com.bronze.boiler.member.dto.MemberRequest;
import com.bronze.boiler.member.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@RequestBody MemberRequest memberRequest) throws NoSuchAlgorithmException {
        return new ResponseEntity(memberService.createMember(memberRequest), HttpStatus.OK);
    }
}
