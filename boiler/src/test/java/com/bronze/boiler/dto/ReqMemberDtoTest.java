package com.bronze.boiler.dto;


import com.bronze.boiler.domain.member.dto.ReqMemberDto;
import com.bronze.boiler.domain.member.enums.Role;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReqMemberDtoTest {
    @Test
    void 회원추가_이름빈값입력_예외발생(){

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ReqMemberDto>> constraintViolations = validator.validate(ReqMemberDto.builder()
                .email("email@email")
                .password("1234")
                .role(Role.USER)
                .build());
        assertThat(constraintViolations.size()).isEqualTo(1);

    }

    @Test
    void 회원추가_이메일빈값입력_예외발생(){

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ReqMemberDto>> constraintViolations = validator.validate(ReqMemberDto.builder()
                .name("name")
                .password("1234")
                .role(Role.USER)
                .build());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    void 회원추가_패스워드빈값입력_예외발생(){

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ReqMemberDto>> constraintViolations = validator.validate(ReqMemberDto.builder()
                .name("aewawf")
                .email("email@email")
                .role(Role.USER)
                .build());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }


}
