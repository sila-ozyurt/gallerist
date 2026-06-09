package com.hediyesilaozyurt.dto.authDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRequest {

    @NotBlank(message = "refresh token cannot be blank null or empty")
    private String token;
}
