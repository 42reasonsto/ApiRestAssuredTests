package api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder(setterPrefix = "set")
public class DeleteOrderRequest {
    public Long userId;

    public String orderId;
}
