package servicetemplate.userservice.service;

import servicetemplate.userservice.data.dto.AuthDto;
import servicetemplate.userservice.data.model.AccessTokenModel;

public interface KeycloakService {

  AccessTokenModel authenticate(AuthDto authDto);

  AccessTokenModel refreshToken(String refreshToken);
}
