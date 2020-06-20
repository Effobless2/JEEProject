package com.esgi.group5.jeeproject.infrastructure.web.controllers.opinions;
/*
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.OpinionDAO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OpinionControllerTest {
    @LocalServerPort
    private int port;

    @MockBean
    private IOpinionService service;

    @BeforeEach
    void setup(){
        RestAssured.port = port;
    }


    @Test
    void should_list_all_opinions(){
        List<OpinionDAO> beers =
                given()
                        .log().all()
                        .when()
                        .get("/opinions")
                        .then()
                        .extract()
                        .jsonPath()
                        .getList(".", OpinionDAO.class);
        assertThat(beers).isNotNull();
        assertThat(beers).hasSize(0);
    }

    @Test
    void should_create_new_opinion(){
        OpinionDAO test = new OpinionDAO();
        test.setName("test");
        List<OpinionDAO> mockList = new ArrayList<>();
        mockList.add(test);
        when(service.add(test)).thenReturn((long) 1);
        when(service.get()).thenReturn(mockList);
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

        assertThat(testId).isEqualTo(1);
        get("/opinions" ).then().body("$", hasSize(1));
    }
}
*/