package com.hediyesilaozyurt.services.authenticationService;

import com.hediyesilaozyurt.dto.authDto.AuthRequest;
import com.hediyesilaozyurt.dto.authDto.AuthResponse;
import com.hediyesilaozyurt.entities.enums.UserRole;

public interface IAuthenticationService {

    public AuthResponse register(AuthRequest request, UserRole role);

    public AuthResponse login(AuthRequest request);
}
