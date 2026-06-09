package com.hediyesilaozyurt.controller.authController.impl;

import com.hediyesilaozyurt.controller.authController.IRestAuthController;
import com.hediyesilaozyurt.controller.base.RestBaseController;
import com.hediyesilaozyurt.dto.authDto.AuthRequest;
import com.hediyesilaozyurt.dto.authDto.AuthResponse;
import com.hediyesilaozyurt.dto.authDto.RefreshTokenRequest;
import com.hediyesilaozyurt.entities.base.RootEntity;
import com.hediyesilaozyurt.entities.enums.UserRole;
import com.hediyesilaozyurt.services.authenticationService.IAuthenticationService;
import com.hediyesilaozyurt.services.authenticationService.IRefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/api/auth")
@RequiredArgsConstructor
public class RestAuthControllerImpl extends RestBaseController implements IRestAuthController {

    private final IAuthenticationService authenticationService;

    private final IRefreshTokenService refreshTokenService;

    @PostMapping("/login")
    @Override
    public ResponseEntity<RootEntity<AuthResponse>> login(@RequestBody @Valid AuthRequest request) {
        AuthResponse response=authenticationService.login(request);
        return respond(HttpStatus.OK,response);
    }

    @PostMapping("/register")
    @Override
    public ResponseEntity<RootEntity<AuthResponse>> register(@RequestBody @Valid AuthRequest request) {
        AuthResponse response=authenticationService.register(request, UserRole.CUSTOMER);
        return respond(HttpStatus.CREATED,response);
    }

    @Override
    @PostMapping("/register/admin")
    public ResponseEntity<RootEntity<AuthResponse>> registerAdmin(@RequestBody @Valid AuthRequest request) {
        AuthResponse response=authenticationService.register(request,UserRole.ADMIN);
        return respond(HttpStatus.CREATED,response);
    }

    @PostMapping("/refresh-token")
    @Override
    public ResponseEntity<RootEntity<AuthResponse>> refresh(@RequestBody @Valid RefreshTokenRequest request) {
        AuthResponse response=refreshTokenService.refreshToken(request);
        return respond(HttpStatus.OK,response);
    }

    @Override
    @PostMapping("/logout")
    public ResponseEntity<RootEntity<String>> logout(@RequestBody @Valid  RefreshTokenRequest request) {
        refreshTokenService.revokeToken(request.getToken());
        return respond(HttpStatus.OK,"loggged out successfully");
    }



    /* @Override
    @PostMapping("/logout-all")
    public RootEntity<String> logoutAll(
            @RequestParam Long userId) {
        //   ^^^^^^^^^^^
        //   Normalde bu userId JWT'den alınır
        //   JwtService hazır olunca güncelleriz

        refreshTokenService.revokeAllUserTokens(userId);
        return RootEntity.ok("Logged out from all devices");
    }*/
}
