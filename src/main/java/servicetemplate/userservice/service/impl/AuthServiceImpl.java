package servicetemplate.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import servicetemplate.userservice.data.dto.AuthDto;
import servicetemplate.userservice.data.model.AccessTokenModel;
import servicetemplate.userservice.service.AuthService;
import servicetemplate.userservice.service.KeycloakService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final KeycloakService keycloakService;

  @Override
  public AccessTokenModel authenticate(AuthDto authDto) {
    return keycloakService.authenticate(authDto);
  }

  @Override
  public AccessTokenModel refreshToken(String refreshToken) {
    return keycloakService.refreshToken(refreshToken);
  }
}
