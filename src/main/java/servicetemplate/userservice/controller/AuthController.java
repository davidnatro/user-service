package servicetemplate.userservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import servicetemplate.userservice.data.dto.AuthDto;
import servicetemplate.userservice.data.dto.RefreshDto;
import servicetemplate.userservice.data.model.AccessTokenModel;
import servicetemplate.userservice.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping
  public ResponseEntity<AccessTokenModel> authenticate(@Valid @RequestBody AuthDto authDto) {
    return ResponseEntity.ok(authService.authenticate(authDto));
  }

  @PostMapping("/refresh")
  public ResponseEntity<AccessTokenModel> refreshToken(@Valid @RequestBody RefreshDto refreshDto) {
    return ResponseEntity.ok(authService.refreshToken(refreshDto.refreshToken()));
  }
}
