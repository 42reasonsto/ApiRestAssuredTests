package api.steps;

import api.models.*;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

@Setter
@Getter
public class ApiSteps {
    private String bearerToken;

    private String orderId;

    public Response postAuthorizationResponse() {
        PostAuthorizationRequest postAuth =  PostAuthorizationRequest.builder()
                .setEmail("ivanov@mail.ru")
                .setPassword("1234")
                .build();

        Response resp =  given()
                .spec(SpecHelper.RequestSpec())
                .when()
                .body(postAuth)
                .post("/auth/authenticate")
                .then()
                .spec(SpecHelper.ResponseSpec())
                .extract()
                .response();

        bearerToken = resp.jsonPath()
                .getString("token");

        return resp;
    }

    public Response getTariffsResponse() {
        return given()
                .spec(SpecHelper.RequestSpec(bearerToken))
                .when()
                .get("/loan-service/getTariffs")
                .then()
                .spec(SpecHelper.ResponseSpec())
                .extract()
                .response();
    }

    public Response postLoanResponse(Long userId, int tariffId) {
        PostLoanRequest postLoanRequest = PostLoanRequest.builder()
                .setUserId(userId)
                .setTariffId(tariffId)
                .build();

        Response resp = given()
                .spec(SpecHelper.RequestSpec(bearerToken))
                .when()
                .body(postLoanRequest)
                .post("/loan-service/order")
                .then()
                .spec(SpecHelper.ResponseSpec())
                .extract()
                .response();

        orderId=resp.jsonPath()
                .getString("data.orderId");

        return resp;
    }

    public Response getStatusOrderResponse() {
        return given()
                .spec(SpecHelper.RequestSpec(bearerToken))
                .when()
                .get(String.format("/loan-service/getStatusOrder?orderId=%s", orderId))
                .then()
                .spec(SpecHelper.ResponseSpec())
                .extract()
                .response();
    }

    public Response getStatusOrderResponse(String orderIdd) {
        return given()
                .spec(SpecHelper.RequestSpec(bearerToken))
                .when()
                .get(String.format("/loan-service/getStatusOrder?orderId=%s", orderIdd))
                .then()
                .spec(SpecHelper.ResponseSpec())
                .extract()
                .response();
    }

    public Response deleteOrderResponse(Long userId) {
        DeleteOrderRequest deleteOrderRequest = DeleteOrderRequest.builder()
                .setUserId(1L)
                .setOrderId(orderId)
                .build();

        return given()
                .spec(SpecHelper.RequestSpec(bearerToken))
                .when()
                .body(deleteOrderRequest)
                .delete("/loan-service/deleteOrder")
                .then()
                .spec(SpecHelper.ResponseSpec())
                .extract()
                .response();
    }

    public Response deleteOrderResponse(Long userId,String orderIdd) {
        DeleteOrderRequest deleteOrderRequest = DeleteOrderRequest.builder()
                .setUserId(1L)
                .setOrderId(orderIdd)
                .build();

        return given()
                .spec(SpecHelper.RequestSpec(bearerToken))
                .when()
                .body(deleteOrderRequest)
                .delete("/loan-service/deleteOrder")
                .then()
                .spec(SpecHelper.ResponseSpec())
                .extract()
                .response();
    }

    public void checkGetTariffs(Object[] response) {
        Assertions.assertNotNull(response);
        Assertions.assertEquals(3,response.length);
        for (int i=0; i<3; i++) {
            GetTariffsResponse getTariffsResponse = (GetTariffsResponse) response[i];
            Assertions.assertNotNull(getTariffsResponse.getInterestRate());
            Assertions.assertNotNull(Arrays.stream(TariffsTypeVariantsArray.typesArray()).anyMatch(getTariffsResponse.getType()::contains));
        }
    }

    public void checkPostOrderId() {
        Assertions.assertNotNull(orderId);
    }

    public void checkGetOrderStatus(String inputStr) {
        Assertions.assertNotNull(Arrays.stream(StatusOrderVariantsArray.orderStatusArray()).anyMatch(inputStr::contains));
    }

    public void checkError(String code, String message) {
        Assertions.assertNotNull(Arrays.stream(ErrorVariantsArrays.codeArray()).anyMatch(code::contains));
        Assertions.assertNotNull(Arrays.stream(ErrorVariantsArrays.messageArray()).anyMatch(message::contains));
    }

    public void checkStatusCode(Response response, int httpstatus) {
        Assertions.assertEquals(httpstatus, response.getStatusCode());
    }
}
