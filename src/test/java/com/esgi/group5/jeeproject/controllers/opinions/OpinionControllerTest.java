package com.esgi.group5.jeeproject.controllers.opinions;

import com.esgi.group5.jeeproject.models.Opinion;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OpinionControllerTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup(){
        RestAssured.port = port;
    }


    @Test
    void should_list_all_opinions(){
        List<Opinion> beers =
                given()
                        .log().all()
                        .when()
                        .get("/opinions")
                        .then()
                        .extract()
                        .jsonPath()
                        .getList(".", Opinion.class);
        assertThat(beers).isNotNull();
        assertThat(beers).hasSize(0);
    }

    @Test
    void should_create_new_opinion(){
        Opinion test = new Opinion();
        int testId =
                given()
                        .contentType(ContentType.JSON)
                        .body(test)
                        .when()
                        .post("/opinions")
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(int.class);

        assertThat(testId).isEqualTo(0);
        get("/opinions" ).then().body("$", hasSize(1));
    }
}
