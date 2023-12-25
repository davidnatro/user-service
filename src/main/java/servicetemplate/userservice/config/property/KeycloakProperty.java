package servicetemplate.userservice.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application.keycloak")
public class KeycloakProperty {

  private String clientId;
  private String serverUrl;
  private String realm;
  private String clientSecret;
  private Admin admin;

  @Getter
  @Setter
  public static class Admin {

    private String username;
    private String password;
  }
}
