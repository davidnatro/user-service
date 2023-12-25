package servicetemplate.userservice.client;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import servicetemplate.userservice.config.FeignConfig;
import servicetemplate.userservice.data.dto.KeycloakAuthDto;
import servicetemplate.userservice.data.dto.KeycloakRefreshDto;
import servicetemplate.userservice.data.model.AccessTokenModel;

@FeignClient(
    name = "keycloakClient", url = "${application.keycloak.server-url}",
    configuration = FeignConfig.class)
public interface KeycloakClient {

  @PostMapping(
      path = "/realms/${application.keycloak.realm}/protocol/openid-connect/token",
      consumes = APPLICATION_FORM_URLENCODED_VALUE)
  AccessTokenModel authenticate(@RequestBody KeycloakAuthDto authDto);

  @PostMapping(
      value = "/realms/${application.keycloak.realm}/protocol/openid-connect/token",
      consumes = APPLICATION_FORM_URLENCODED_VALUE)
  AccessTokenModel refreshToken(@RequestBody KeycloakRefreshDto refreshDto);
}
