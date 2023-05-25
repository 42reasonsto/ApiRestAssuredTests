package api.models;

import lombok.Builder;

@Builder(setterPrefix = "set")
public class PostLoanRequest {
    public Long userId;

    public Integer tariffId;
}
