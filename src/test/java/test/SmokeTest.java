package test;

import api.Tools.Mapper;
import api.models.GetTariffsResponse;
import api.steps.ApiSteps;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class SmokeTest {
    Mapper mapper = new Mapper();

    ApiSteps apiSteps = new ApiSteps();
    @Test
    public void Authorization() {
        Response response = apiSteps.postAuthorizationResponse();

        apiSteps.checkStatusCode(response,200);
    }

    @Test
    public void GetTariff() {
        Response auth = apiSteps.postAuthorizationResponse();

        Response response = apiSteps.getTariffsResponse();

        var tariffsResponse = response.getBody().jsonPath().getList("data.tariffs", GetTariffsResponse.class).toArray();

        apiSteps.checkGetTariffs(tariffsResponse);
        apiSteps.checkStatusCode(response,200);
    }

    @Test
    public void PostOrderSuccess() {
        apiSteps.postAuthorizationResponse();

        Response response = apiSteps.postLoanResponse(1L,1);

        apiSteps.checkStatusCode(response, 200);

        apiSteps.checkPostOrderId();
    }

    @Test
    public void PostOrderUnsuccess() {
        apiSteps.postAuthorizationResponse();

        Response response = apiSteps.postLoanResponse(1L,12345);

        apiSteps.checkStatusCode(response, 400);

        apiSteps.checkError(mapper.responseToPojoError(response).getCode(),mapper.responseToPojoError(response).getMessage());
    }

    @Test
    public void GetStatusOrderSuccess() {
        apiSteps.postAuthorizationResponse();

        apiSteps.postLoanResponse(1L,3);

        Response responseGet = apiSteps.getStatusOrderResponse();

        apiSteps.checkGetOrderStatus(mapper.responseToPojoData(responseGet).getOrderStatus());
        apiSteps.checkStatusCode(responseGet,200);
    }

    @Test
    public void GetStatusOrderUnsuccess() {
        apiSteps.postAuthorizationResponse();

        apiSteps.postLoanResponse(1L,2);

        UUID uuid = UUID.randomUUID();

        Response responseGetError = apiSteps.getStatusOrderResponse(uuid.toString().toLowerCase());

        apiSteps.checkError(mapper.responseToPojoError(responseGetError).getCode(),mapper.responseToPojoError(responseGetError).getMessage());
        apiSteps.checkStatusCode(responseGetError,400);
    }

    @Test
    public void DeleteOrderSuccess() {
        apiSteps.postAuthorizationResponse();

        apiSteps.postLoanResponse(1L,2);

        Response responseDelete = apiSteps.deleteOrderResponse(1L);

        apiSteps.checkStatusCode(responseDelete,200);
    }

    @Test
    public void DeleteOrderUnsuccess() {
        apiSteps.postAuthorizationResponse();

        UUID uuid = UUID.randomUUID();

        Response responseDelete = apiSteps.deleteOrderResponse(1L,uuid.toString().toLowerCase());

        apiSteps.checkError(mapper.responseToPojoError(responseDelete).getCode(),mapper.responseToPojoError(responseDelete).getMessage());
        apiSteps.checkStatusCode(responseDelete,400);
    }
}
