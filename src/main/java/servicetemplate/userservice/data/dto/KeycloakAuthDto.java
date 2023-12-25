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
public class KeycloakAuthDto {

  @FormProperty("client_id")
  private String clientId;

  @FormProperty("client_secret")
  private String clientSecret;

  @FormProperty("username")
  private String username;

  @FormProperty("password")
  private String password;

  @FormProperty("grant_type")
  private String grantType;
}
