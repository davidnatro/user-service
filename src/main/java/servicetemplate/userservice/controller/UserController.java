package servicetemplate.userservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @GetMapping
  public ResponseEntity<Page<UserModel>> getAllUsers(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok()
        .build();
  }

  @GetMapping("/{username}")
  public ResponseEntity<UserModel> getUserByUsername(@PathVariable String username) {
    return ResponseEntity.ok()
        .build();
  }

  @PostMapping
  public ResponseEntity<UserModel> registerUser(
      @Valid @RequestBody RegistrationDto registrationDto) {
    userService.createRegistrationRequest(registrationDto);
    return ResponseEntity.accepted()
        .build();
  }

  @PutMapping
  public ResponseEntity<UserModel> updateUser() {
    return ResponseEntity.ok()
        .build();
  }

  @DeleteMapping("/{username}")
  public ResponseEntity<Void> deleteUser(@PathVariable String username) {
    return ResponseEntity.noContent()
        .build();
  }
}
