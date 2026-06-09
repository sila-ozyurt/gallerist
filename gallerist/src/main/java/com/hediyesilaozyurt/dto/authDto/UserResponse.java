package com.hediyesilaozyurt.dto.authDto;

import com.hediyesilaozyurt.entities.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponse {

    private Long id;

    private String username;

    private UserRole role;

    private LocalDateTime createdAt;
}
