package servicetemplate.userservice.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthDto(@NotBlank String username,
                      @JsonIgnore @NotNull char[] password) { }
