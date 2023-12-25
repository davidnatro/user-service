package servicetemplate.userservice.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccessTokenModel(@JsonProperty("access_token") String accessToken,
                               @JsonProperty("refresh_token") String refreshToken) { }
