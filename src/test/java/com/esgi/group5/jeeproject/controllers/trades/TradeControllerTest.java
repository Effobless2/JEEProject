package com.esgi.group5.jeeproject.controllers.trades;

import com.esgi.group5.jeeproject.models.Trade;
import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.services.contracts.ITradeService;
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
    private ITradeService service;

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
    void should_create_new_trade(){
        Trade test = new Trade();
        test.setName("test");
        List<Trade> mockList = new ArrayList<>();
        mockList.add(test);
        when(service.add(test)).thenReturn((long) 1);
        when(service.get()).thenReturn(mockList);
        int tradeId =
                given()
                    .contentType(ContentType.JSON)
                    .body(test)
                    .when()
                    .post("/trades")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .extract()
                    .as(int.class);

        assertThat(tradeId).isEqualTo(1);
        get("/trades" ).then().body("$", hasSize(1));
    }
}
