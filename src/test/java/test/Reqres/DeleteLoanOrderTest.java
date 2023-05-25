package test.Reqres;

import api.Tools.Mapper;
import api.steps.ApiSteps;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class DeleteLoanOrderTest {
    Mapper mapper = new Mapper();

    static ApiSteps apiSteps = new ApiSteps();

    @Test
    public void DeleteOrderSuccess() {
        apiSteps.postAuthorizationResponse();

        apiSteps.postLoanResponse(1L,1);

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
