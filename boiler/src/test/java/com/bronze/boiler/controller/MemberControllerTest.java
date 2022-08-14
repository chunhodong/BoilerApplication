package com.bronze.boiler.controller;

import com.bronze.boiler.domain.member.dto.ResMemberDto;
import com.bronze.boiler.domain.member.enums.MemberExceptionType;
import com.bronze.boiler.domain.member.enums.MemberStatus;
import com.bronze.boiler.domain.member.enums.Role;
import com.bronze.boiler.exception.MemberException;
import com.bronze.boiler.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;


    @MockBean
    MemberService memberService;


    @Test
    void 컨트롤러_회원조회_회원데이터확인() throws Exception {

        doReturn(ResMemberDto.builder()
                .id(13l)
                .name("회원1")
                .email("test@test.com")
                .status(MemberStatus.NORMAL)
                .role(Role.USER)
                .lastLogin(LocalDateTime.now().minusDays(1))
                .build()).when(memberService).getMember(anyLong());
        mockMvc.perform(get("/members/13"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("회원1"))
                .andExpect(jsonPath("$.email").value("test@test.com"))
                .andExpect(jsonPath("$.status").value("NORMAL"))
                .andExpect(jsonPath("$.role").value("USER"))
                .andDo(print());


    }

    @Test
    void 컨트롤러_회원조회_없는회원일때_예외발생() throws Exception {

        doThrow(new MemberException(MemberExceptionType.NONE_EXIST_MEMBER)).when(memberService).getMember(anyLong());
        mockMvc.perform(get("/members/13"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("회원1"))
                .andExpect(jsonPath("$.email").value("test@test.com"))

                .andDo(print());


    }

}
