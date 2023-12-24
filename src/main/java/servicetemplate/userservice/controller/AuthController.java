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

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  @PostMapping
  public ResponseEntity<Void> authenticate(@Valid @RequestBody AuthDto authDto) {
    return null;
  }

  @PostMapping("/refresh")
  public ResponseEntity<Void> refreshToken(@Valid @RequestBody RefreshDto refreshDto) {
    return null;
  }
}
