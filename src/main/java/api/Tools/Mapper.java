package api.Tools;

import api.models.LoanResponseSuccess;
import api.models.LoanResponseUnsuccess;
import io.restassured.response.Response;

public class Mapper {
    public LoanResponseSuccess responseToPojoData(Response response) {
        return response.getBody().jsonPath().getObject("data.", LoanResponseSuccess.class);
    }

    public LoanResponseUnsuccess responseToPojoError(Response response) {
        return response.getBody().jsonPath().getObject("error.", LoanResponseUnsuccess.class);
    }
}
