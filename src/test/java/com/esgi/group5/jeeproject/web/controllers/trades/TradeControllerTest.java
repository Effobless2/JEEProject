package com.esgi.group5.jeeproject.web.controllers.trades;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.use_cases.trades.CreateTradeService;
import com.esgi.group5.jeeproject.domain.use_cases.trades.ReadTradeService;
import com.esgi.group5.jeeproject.persistence.datatbase.daos.TradeDAO;
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
public class TradeControllerTest {
    @LocalServerPort
    private int port;

    @MockBean
    private ReadTradeService readTradeService;

    @MockBean
    private CreateTradeService createTradeService;

    @BeforeEach
    void setup(){
        RestAssured.port = port;
    }

    @Test
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

    @Test
    void should_not_create_new_trade_if_no_valid_token(){
        Trade test = new Trade();
        test.setName("test");
        List<Trade> mockList = new ArrayList<>();
        mockList.add(test);
        when(createTradeService.createTrade(test, new User())).thenReturn(test);
        when(readTradeService.get()).thenReturn(mockList);
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
