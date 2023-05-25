package test.Reqres;

import api.Tools.Mapper;
import api.steps.ApiSteps;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PostLoanOrderTest {
    Mapper mapper = new Mapper();

    ApiSteps apiSteps = new ApiSteps();


    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    public void PostOrderSuccess(int tariffId) {
        apiSteps.postAuthorizationResponse();

        Response response = apiSteps.postLoanResponse(1L,tariffId);

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
}
