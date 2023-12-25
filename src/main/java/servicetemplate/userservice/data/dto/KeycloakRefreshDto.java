package servicetemplate.userservice.data.dto;

import feign.form.FormProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KeycloakRefreshDto {

  @FormProperty("client_id")
  private String clientId;

  @FormProperty("client_secret")
  private String clientSecret;

  @FormProperty("refresh_token")
  private String refreshToken;

  @FormProperty("grant_type")
  private String grant_Type;
}
