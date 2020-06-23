package com.esgi.group5.jeeproject.infrastructure.web.controllers.beers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.use_cases.beers.*;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.BeerDAO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
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
public class BeerDAOControllerTest {
    @LocalServerPort
    private int port;

    @MockBean
    private ReadBeer readBeer;

    @MockBean
    private CreateBeer createBeer;

    @MockBean
    private UpdateBeer updateBeer;

    @MockBean
    private DeleteBeer deleteBeer;

    @BeforeEach
    void setup(){
        RestAssured.port = port;
    }

    void should_list_all_beers(){
        List<Beer> beers =
                given()
                        .log().all()
                        .when()
                        .get("/beers")
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .jsonPath()
                        .getList(".", Beer.class);
        assertThat(beers).isNotNull();
        assertThat(beers).hasSize(0);
    }

    void should_get_one_beer_if_exist(){
        Beer test = new Beer();
        test.setName("test");
        when(readBeer.getBeerById(1L)).thenReturn(test);
        BeerDAO result =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get("/beers/1")
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .as(BeerDAO.class);

        assertThat(result).isEqualTo(test);
    }

    void should_get_null_beer_if_not_exist(){
        when(readBeer.getBeerById(1L)).thenReturn(null);
        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/beers/1")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .and()
            .contentType(emptyOrNullString());
    }

    void should_create_new_beer(){
        Beer test = new Beer();
        test.setName("test");
        List<Beer> mockList = new ArrayList<>();
        mockList.add(test);
        when(createBeer.createBeer(test)).thenReturn(test);
        when(readBeer.getAllBeers()).thenReturn(mockList);
        given()
                .contentType(ContentType.JSON)
                .body(test)
                .when()
                .post("/beers")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    void should_update_beer(){
        Beer test = new Beer();
        test.setName("test");
        List<Beer> mockList = new ArrayList<>();
        mockList.add(test);
        when(updateBeer.updateBeer(test)).thenReturn(test);
        when(readBeer.getBeerById(1L)).thenReturn(test);

        when(readBeer.getAllBeers()).thenReturn(mockList);
        given()
                .contentType(ContentType.JSON)
                .body(test)
                .when()
                .put("/beers")
                .then()
                .statusCode(HttpStatus.OK.value());

        get("/beers" ).then().body("$", hasSize(1));
    }

    void should_not_update_beer_if_id_invalid(){
        Beer test = new Beer();
        test.setName("test");
        test.setId((long) 1);
        List<Beer> mockList = new ArrayList<>();
        mockList.add(test);

        when(updateBeer.updateBeer(test)).thenReturn(null);

        when(readBeer.getAllBeers()).thenReturn(mockList);
        given()
            .contentType(ContentType.JSON)
            .body(test)
            .when()
            .put("/beers")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value());

        get("/beers" ).then().body("$", hasSize(1));
    }

    void should_return_ok_for_deleting_existing_beer(){
        when(deleteBeer.deleteBeer(1L)).thenReturn(true);
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/beers/1")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    void should_return_not_found_for_deleting_non_existing_beer(){
        when(deleteBeer.deleteBeer(1L)).thenReturn(false);
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/beers/1")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }
}
