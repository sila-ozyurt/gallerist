package com.hediyesilaozyurt.dto.authDto;

import com.hediyesilaozyurt.entities.enums.UserRole;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String username;

    private UserRole role;

    private String accessToken;

    private String refreshToken;

    private String message;

}
