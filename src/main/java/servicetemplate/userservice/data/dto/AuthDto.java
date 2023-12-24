package servicetemplate.userservice.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthDto(@NotBlank String username,
                      @NotNull char[] password) { }
