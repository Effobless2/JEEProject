package com.esgi.group5.jeeproject.controllers.beers;

import com.esgi.group5.jeeproject.models.Beer;
import com.esgi.group5.jeeproject.services.contracts.IBeerService;
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
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BeerControllerTest {
    @LocalServerPort
    private int port;

    @MockBean
    private IBeerService service;

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
    void should_get_one_beer_if_exist(){
        Beer test = new Beer();
        test.setName("test");
        when(service.get(1)).thenReturn(test);
        Beer result =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get("/beers/1")
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .as(Beer.class);

        assertThat(result).isEqualTo(test);
    }

    @Test
    void should_get_null_beer_if_not_exist(){
        when(service.get(1)).thenReturn(null);
        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/beers/1")
            .then()
            .statusCode(HttpStatus.OK.value())
            .and()
            .contentType(emptyOrNullString());
    }

    @Test
    void should_create_new_beer(){
        Beer test = new Beer();
        test.setName("test");
        List<Beer> mockList = new ArrayList<>();
        mockList.add(test);
        when(service.add(test)).thenReturn((long) 1);
        when(service.get()).thenReturn(mockList);
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

        assertThat(testId).isEqualTo(1);
        get("/beers" ).then().body("$", hasSize(1));
    }

    @Test
    void should_update_beer(){
        Beer test = new Beer();
        test.setName("test");
        List<Beer> mockList = new ArrayList<>();
        mockList.add(test);
        when(service.update(test)).thenReturn(true);
        when(service.get((long) 1)).thenReturn(test);

        when(service.get()).thenReturn(mockList);
        Beer result =
                given()
                        .contentType(ContentType.JSON)
                        .body(test)
                        .when()
                        .put("/beers")
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .as(Beer.class);

        assertThat(result).isEqualTo(test);

        get("/beers" ).then().body("$", hasSize(1));
    }

    @Test
    void should_not_update_beer_if_id_invalid(){
        Beer test = new Beer();
        test.setName("test");
        test.setId((long) 1);
        List<Beer> mockList = new ArrayList<>();
        mockList.add(test);

        when(service.update(test)).thenReturn(false);

        when(service.get()).thenReturn(mockList);
        given()
            .contentType(ContentType.JSON)
            .body(test)
            .when()
            .put("/beers")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value());

        get("/beers" ).then().body("$", hasSize(1));
    }

    @Test
    void should_return_ok_for_deleting_existing_beer(){
        when(service.delete((long) 1)).thenReturn(true);
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/beers/1")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void should_return_not_found_for_deleting_non_existing_beer(){
        when(service.delete((long) 1)).thenReturn(false);
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/beers/1")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
