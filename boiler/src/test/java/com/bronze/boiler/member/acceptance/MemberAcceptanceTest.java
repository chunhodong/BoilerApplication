package com.bronze.boiler.member.acceptance;

import com.bronze.boiler.AcceptanceTest;
import com.bronze.boiler.member.dto.MemberRequest;
import com.bronze.boiler.member.ui.MemberController;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class MemberAcceptanceTest extends AcceptanceTest {

    private MemberController controller;

    @Test
    void 회원추가() {
        RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(MemberRequest.builder().build())
                .when().post("/members")
                .then().log().all()
                .extract();
    }


}
