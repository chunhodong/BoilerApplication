package com.bronze.boiler.member.application;

import com.bronze.boiler.member.dto.MemberRequest;
import com.bronze.boiler.member.dto.MemberResponse;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;


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
                MemberResponse member = memberService.createMember(MemberRequest.builder()
                                .email("yhms4432@nate.com")
                                .name("kim")
                                .password("eawoijf")
                        .build());

                assertThat(member).isNotNull();

            }
        }
    }
}

