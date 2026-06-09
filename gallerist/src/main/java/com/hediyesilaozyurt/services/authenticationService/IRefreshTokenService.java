package com.hediyesilaozyurt.services.authenticationService;

import com.hediyesilaozyurt.dto.authDto.AuthResponse;
import com.hediyesilaozyurt.dto.authDto.RefreshTokenRequest;
import com.hediyesilaozyurt.entities.authEntities.RefreshToken;

import java.util.Optional;

public interface IRefreshTokenService {

    public RefreshToken createRefreshToken(Long id);

    public Optional<RefreshToken> findByToken(RefreshTokenRequest request);

    public RefreshToken verifyExpiryDate(RefreshToken token);

    public AuthResponse refreshToken(RefreshTokenRequest request);

    public void revokeToken(String token);

    //public void revokeAllUserTokens(Long userId);
}
