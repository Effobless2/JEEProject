package com.esgi.group5.jeeproject.controllers.beers;

import com.esgi.group5.jeeproject.models.Beer;
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
public class BeerControllerTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup(){
        RestAssured.port = port;
    }


    @Test
    void should_list_all_beers(){
        List<Beer> beers =
                given()
                        .log().all()
                        .when()
                        .get("/beers")
                        .then()
                        .extract()
                        .jsonPath()
                        .getList(".", Beer.class);
        assertThat(beers).isNotNull();
        assertThat(beers).hasSize(0);
    }

    @Test
    void should_create_new_beer(){
        Beer test = new Beer();
        int testId =
                given()
                        .contentType(ContentType.JSON)
                        .body(test)
                        .when()
                        .post("/beers")
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(int.class);

        assertThat(testId).isEqualTo(0);
        get("/beers" ).then().body("$", hasSize(1));
    }
}
