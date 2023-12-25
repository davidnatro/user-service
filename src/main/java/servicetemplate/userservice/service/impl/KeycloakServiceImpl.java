package servicetemplate.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import servicetemplate.userservice.client.KeycloakClient;
import servicetemplate.userservice.config.property.KeycloakProperty;
import servicetemplate.userservice.data.dto.AuthDto;
import servicetemplate.userservice.data.dto.KeycloakAuthDto;
import servicetemplate.userservice.data.dto.KeycloakRefreshDto;
import servicetemplate.userservice.data.model.AccessTokenModel;
import servicetemplate.userservice.service.KeycloakService;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {

  private final KeycloakClient keycloakClient;
  private final KeycloakProperty keycloakProperty;

  @Override
  public AccessTokenModel authenticate(AuthDto authDto) {
    KeycloakAuthDto keycloakAuthDto = new KeycloakAuthDto(keycloakProperty.getClientId(),
                                                          keycloakProperty.getClientSecret(),
                                                          authDto.username(), authDto.password(),
                                                          OAuth2Constants.PASSWORD);

    try {
      return keycloakClient.authenticate(keycloakAuthDto);
    } catch (Exception exception) {
      log.error("User '{}' authentication failed: '{}'", authDto.username(),
                exception.getMessage());
      throw new AccessDeniedException("Invalid credentials");
    }
  }

  @Override
  public AccessTokenModel refreshToken(String refreshToken) {
    KeycloakRefreshDto keycloakRefreshDto = new KeycloakRefreshDto(keycloakProperty.getClientId(),
                                                                   keycloakProperty.getClientSecret(),
                                                                   refreshToken,
                                                                   OAuth2Constants.REFRESH_TOKEN);

    try {
      return keycloakClient.refreshToken(keycloakRefreshDto);
    } catch (Exception exception) {
      log.error("Refresh token failed: '{}'", exception.getMessage());
      throw new AccessDeniedException("Invalid refresh token");
    }
  }
}
