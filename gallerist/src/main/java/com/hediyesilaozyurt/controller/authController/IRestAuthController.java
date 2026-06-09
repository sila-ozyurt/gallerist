package com.hediyesilaozyurt.controller.authController;

import com.hediyesilaozyurt.dto.authDto.AuthRequest;
import com.hediyesilaozyurt.dto.authDto.AuthResponse;
import com.hediyesilaozyurt.dto.authDto.RefreshTokenRequest;
import com.hediyesilaozyurt.entities.base.RootEntity;
import org.springframework.http.ResponseEntity;

public interface IRestAuthController {

    public ResponseEntity<RootEntity<AuthResponse>> login(AuthRequest request);

    public ResponseEntity<RootEntity<AuthResponse>> register(AuthRequest request);

    public ResponseEntity<RootEntity<AuthResponse>> registerAdmin(AuthRequest request);

    public ResponseEntity<RootEntity<AuthResponse>> refresh(RefreshTokenRequest request);

    public ResponseEntity<RootEntity<String>> logout(RefreshTokenRequest request);

    // public RootEntity<String> logoutAll();
}
