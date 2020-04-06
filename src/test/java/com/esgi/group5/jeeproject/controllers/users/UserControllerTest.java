package com.esgi.group5.jeeproject.controllers.users;

import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.services.contracts.IUserService;
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
public class UserControllerTest {
    @LocalServerPort
    private int port;

    @MockBean
    private IUserService service;

    @BeforeEach
    void setup(){
        RestAssured.port = port;
    }

    @Test
    void should_list_all_users(){
        List<User> users =
                given()
                    .log().all()
                .when()
                    .get("/users")
                .then()
                    .extract()
                    .jsonPath()
                    .getList(".", User.class);
        assertThat(users).isNotNull();
        assertThat(users).hasSize(0);
    }

    @Test
    void should_create_new_user(){
        User test = new User();
        test.setName("test");
        List<User> mockList = new ArrayList<>();
        mockList.add(test);
        when(service.add(test)).thenReturn(0);
        when(service.get()).thenReturn(mockList);

        int testId =
            given()
                .contentType(ContentType.JSON)
                .body(test)
            .when()
                .post("/users")
            .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(int.class);

        assertThat(testId).isEqualTo(0);
        get("/users" ).then().body("$", hasSize(1));
    }
}
