package servicetemplate.userservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import servicetemplate.userservice.config.property.KeycloakProperty;

@Configuration
@RequiredArgsConstructor
public class KeycloakConfig {

  private final KeycloakProperty property;
}
