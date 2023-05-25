package test.Reqres;

import api.models.GetTariffsResponse;
import api.steps.ApiSteps;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class GetTariffsTest {
    ApiSteps apiSteps = new ApiSteps();

    @Test
    public void GetTariff() {
        apiSteps.postAuthorizationResponse();

        Response response = apiSteps.getTariffsResponse();

        var tariffsResponse = response.getBody().jsonPath().getList("data.tariffs", GetTariffsResponse.class).toArray();

        apiSteps.checkGetTariffs(tariffsResponse);
        apiSteps.checkStatusCode(response,200);
    }
}
