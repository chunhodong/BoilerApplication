package com.bronze.boiler.member.application;

import com.bronze.boiler.member.dto.MemberRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;


@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Nested
    class DescribeCreate{
        @Nested
        class ContextDuplecateEmail{
            @Test
            void throwException(){

            }
        }

        @Nested
        class ContextMember{
            @Test
            void returnsMember() throws NoSuchAlgorithmException {
                memberService.createMember(MemberRequest.builder()
                                .email("yhms4432@nate.com")
                                .name("kim")
                                .password("eawoijf")
                        .build());
            }
        }
    }
}

