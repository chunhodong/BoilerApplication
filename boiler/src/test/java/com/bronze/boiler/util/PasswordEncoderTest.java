package com.bronze.boiler.util;

import com.bronze.boiler.common.PasswordEncoder;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordEncoderTest {

    @Test
    void 비밀번호인코딩_입력값과다르면_조회() throws NoSuchAlgorithmException {
        String result = PasswordEncoder.encrypt("test1234");
        assertThat(result).isNotEqualTo("test1234");
    }
}
