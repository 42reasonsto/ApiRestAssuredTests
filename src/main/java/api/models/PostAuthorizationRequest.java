package api.models;

import lombok.Builder;

@Builder(setterPrefix = "set")
public class PostAuthorizationRequest {
    public String email;

    public String password;
}
