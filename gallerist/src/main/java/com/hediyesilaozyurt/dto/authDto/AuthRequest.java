package com.hediyesilaozyurt.dto.authDto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    @NotBlank(message = "username cannot be null, empty or blank")
    private String username;

    @NotBlank(message = "password cannot be null, empty or blank")
    private String password;
}
