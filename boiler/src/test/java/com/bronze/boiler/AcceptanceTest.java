package com.bronze.boiler;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {
    @LocalServerPort
    private int port;
    @Autowired
    private DatabaseCleanup cleanup;

    @BeforeEach
    public void before() {
        RestAssured.port = port;
        cleanup.execute();
    }
}
