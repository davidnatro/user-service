package servicetemplate.userservice.data.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshDto(@NotBlank String refreshToken) { }
