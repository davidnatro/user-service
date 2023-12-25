package servicetemplate.userservice.data.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistrationDto(@NotBlank String username,
                              @NotBlank @Email String email) { }
