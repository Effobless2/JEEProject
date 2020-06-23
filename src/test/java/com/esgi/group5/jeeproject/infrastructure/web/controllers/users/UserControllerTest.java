package com.esgi.group5.jeeproject.infrastructure.web.controllers.users;

import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.use_cases.users.ReadUser;
import com.esgi.group5.jeeproject.domain.use_cases.users.RegisterUser;
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
public class UserControllerTest {
    @LocalServerPort
    private int port;

    @MockBean
    private ReadUser readUser;

    @MockBean
    private RegisterUser registerUser;

    @BeforeEach
    void setup(){
        RestAssured.port = port;
    }

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

    void should_not_create_new_user_without_token(){
        User test = new User();
        test.setName("test");
        test.setId((long) 1);
        List<User> mockList = new ArrayList<>();
        mockList.add(test);
        when(registerUser.register(test)).thenReturn(test);
        when(readUser.getAllUsers()).thenReturn(mockList);

        long testId =
            given()
                .contentType(ContentType.JSON)
                .body(test)
            .when()
                .post("/users")
            .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .extract()
                .as(long.class);

        assertThat(testId).isEqualTo(1);
        get("/users" ).then().body("$", hasSize(1));
    }
}
