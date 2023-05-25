package test.Reqres;

import api.Tools.Mapper;
import api.models.StatusOrderVariantsArray;
import api.steps.ApiSteps;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class GetLoanStatusOrderTest {
    Mapper mapper = new Mapper();

    ApiSteps apiSteps = new ApiSteps();
    @Test
    public void GetStatusOrderSuccess() {
        apiSteps.postAuthorizationResponse();

        apiSteps.postLoanResponse(1L,1);

        Response responseGet = apiSteps.getStatusOrderResponse();

        apiSteps.checkGetOrderStatus(mapper.responseToPojoData(responseGet).getOrderStatus());
        apiSteps.checkStatusCode(responseGet,200);
    }

    @Test
    public void GetStatusOrderUnsuccess() {
        apiSteps.postAuthorizationResponse();

        apiSteps.postLoanResponse(1L,1);

        UUID uuid = UUID.randomUUID();

        Response responseGetError = apiSteps.getStatusOrderResponse(uuid.toString().toLowerCase());

        apiSteps.checkError(mapper.responseToPojoError(responseGetError).getCode(),mapper.responseToPojoError(responseGetError).getMessage());
        apiSteps.checkStatusCode(responseGetError,400);
    }
}
