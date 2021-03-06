package com.esgi.group5.jeeproject.infrastructure.web.controllers.trades;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.use_cases.trades.CreateTrade;
import com.esgi.group5.jeeproject.domain.use_cases.trades.GetAllTrades;
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
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TradeControllerTest {
    @LocalServerPort
    private int port;

    @MockBean
    private GetAllTrades getAllTrades;

    @MockBean
    private CreateTrade createTrade;

    @BeforeEach
    void setup(){
        RestAssured.port = port;
    }

    void should_list_all_trades(){
        List<Trade> trades =
            given()
                .log().all()
                .when()
                .get("/trades")
                .then()
                .extract()
                .jsonPath()
                .getList(".", Trade.class);
        assertThat(trades).isNotNull();
        assertThat(trades).hasSize(0);
    }

    void should_not_create_new_trade_if_no_valid_token(){
        Trade test = new Trade();
        test.setName("test");
        List<Trade> mockList = new ArrayList<>();
        mockList.add(test);
        when(createTrade.execute(test, new User())).thenReturn(test);
        when(getAllTrades.execute()).thenReturn(mockList);
        given()
            .contentType(ContentType.JSON)
            .body(test)
            .when()
            .post("/trades")
            .then()
            .statusCode(HttpStatus.UNAUTHORIZED.value());
        get("/trades" ).then().body("$", hasSize(0));
    }
}
