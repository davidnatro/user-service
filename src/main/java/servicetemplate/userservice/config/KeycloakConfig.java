package servicetemplate.userservice.config;

import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import servicetemplate.userservice.config.property.KeycloakProperty;

@Configuration
@RequiredArgsConstructor
public class KeycloakConfig {

  private final KeycloakProperty property;

  @Bean
  public Keycloak keycloak() {
    return KeycloakBuilder.builder()
        .serverUrl(property.getServerUrl())
        .realm(property.getRealm())
        .clientId(property.getClientId())
        .grantType(OAuth2Constants.PASSWORD)
        .username(property.getAdmin()
                      .getUsername())
        .password(property.getAdmin()
                      .getPassword())
        .build();
  }
}
