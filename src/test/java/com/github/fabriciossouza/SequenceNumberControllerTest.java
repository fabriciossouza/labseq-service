package com.github.fabriciossouza;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
public class SequenceNumberControllerTest {
    @Test
    public void testGetSequenceNumberStatusCode200() {
        given()
            .pathParam("number", 0)
            .when()
                .get("/labseq/{number}")
            .then()
                .statusCode(200)
                .body(is("0"));

        given()
            .pathParam("number", 1)
                .when().get("/labseq/{number}")
            .then()
                .statusCode(200)
                .body(is("1"));

        given()
            .pathParam("number", 10)
            .when()
                .get("/labseq/{number}")
            .then()
                .statusCode(200)
                .body(is("3"));
    }

    @Test
    public void testGetSequenceNumberIllegalArgument() {
        given()
            .pathParam("number", -22)
            .when()
                .get("/labseq/{number}")
            .then()
                .statusCode(400)
                .body(containsString("{\"message\":\"Validation error: getSequenceNumber.number: The index may be any non-negative integer number\"}"));
    }

    @Test
    public void testGetSequencInvalidNumber() {
        given()
            .pathParam("number", 2147483648L)
            .when()
                .get("/labseq/{number}")
            .then()
                .statusCode(400);

    }
}
