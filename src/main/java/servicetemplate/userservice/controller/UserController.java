package servicetemplate.userservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import servicetemplate.userservice.data.dto.RegistrationDto;
import servicetemplate.userservice.data.model.UserModel;
import servicetemplate.userservice.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserModel> register(@Valid @RequestBody RegistrationDto registrationDto) {
    return ResponseEntity.ok(userService.register(registrationDto));
  }
}
