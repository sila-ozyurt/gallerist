package com.hediyesilaozyurt.services.authenticationService.impl;

import com.hediyesilaozyurt.dto.authDto.AuthResponse;
import com.hediyesilaozyurt.dto.authDto.RefreshTokenRequest;
import com.hediyesilaozyurt.entities.authEntities.RefreshToken;
import com.hediyesilaozyurt.entities.authEntities.User;
import com.hediyesilaozyurt.exception.BaseException;
import com.hediyesilaozyurt.exception.ErrorMessage;
import com.hediyesilaozyurt.exception.MessageType;
import com.hediyesilaozyurt.security.jwt.JwtService;
import com.hediyesilaozyurt.repository.RefreshTokenRepository;
import com.hediyesilaozyurt.repository.UserRepository;
import com.hediyesilaozyurt.services.authenticationService.IRefreshTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class RefreshTokenServiceImpl implements IRefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    //7 days
    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenDurationMs;

    //this method for login and register operations
    @Override
    public RefreshToken createRefreshToken(Long id) {

        //check out user if exists
        User user=userRepository.findById(id)
                .orElseThrow(()->new BaseException(new ErrorMessage("User not found with id : "+id,MessageType.ENTITY_NOT_FOUND)));

        RefreshToken refreshToken=RefreshToken.builder()
                .refreshToken(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusSeconds(refreshTokenDurationMs/1000))
                .user(user)
                .build();

        refreshToken=refreshTokenRepository.save(refreshToken);

        log.info("created new refresh token for user: {}",user.getUsername());
        return refreshToken;

    }

    //this method for rotate operation, expirydate shouldnt be renewed or else user never logs out of the system
    private RefreshToken rotateRefreshToken(RefreshToken oldToken, User user){

        //for an attacker not to use old refresh token again
        oldToken.setRevoked(true);
        refreshTokenRepository.save(oldToken);

        RefreshToken newRefreshToken= RefreshToken.builder()
                .refreshToken(UUID.randomUUID().toString())
                .expiryDate(oldToken.getExpiryDate())
                .user(user)
                .build();

        return refreshTokenRepository.save(newRefreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(RefreshTokenRequest request) {
        return refreshTokenRepository.findByRefreshToken(request.getToken());
    }

    @Override
    public RefreshToken verifyExpiryDate(RefreshToken token) {
        if(!token.isValid()){
            refreshTokenRepository.delete(token);
            throw new BaseException(new ErrorMessage("Refresh token is expired",MessageType.TOKEN_IS_EXPIRED));
        }

        return token;
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        return findByToken(request)
                .map(this::verifyExpiryDate)
                .map(existingToken->{
                    User user=existingToken.getUser();

                    //rotate new refresh token or else an attacker could continuously generate access tokens.
                    RefreshToken newRefreshToken=rotateRefreshToken(existingToken,user);
                    String newAccessToken= jwtService.generateToken(user);

                    log.info("token is renewed for user: {}",user.getUsername());

                    return AuthResponse.builder()
                            .accessToken(newAccessToken)
                            .refreshToken(newRefreshToken.getRefreshToken())
                            .username(user.getUsername())
                            .role(user.getRole())
                            .message("Token refreshed successfully")
                            .build();
                })
                .orElseThrow(()->new BaseException(
                        new ErrorMessage("RefreshToken not found",MessageType.ENTITY_NOT_FOUND)
                ));
    }

    //logout from the current device
    @Override
    public void revokeToken(String token) {

        RefreshToken refreshToken=refreshTokenRepository.findByRefreshToken(token)
                .orElseThrow(()->new BaseException(new ErrorMessage(
                        "Refresh token nout found",
                        MessageType.ENTITY_NOT_FOUND
                )));

        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);
        log.info("refresh token is revoked. User: {}",refreshToken.getUser().getUsername());
    }
/*
    //logout from all devices
    @Override
    public void revokeAllUserTokens(Long userId) {
        //get active refresh tokens
        List<RefreshToken> tokens=refreshTokenRepository.find;

        //revoke them all
        tokens.forEach(t->t.setRevoked(true));

        //save all in one operation
        refreshTokenRepository.saveAll(tokens);

        log.info("all tokens are revoked for user: {}",userId);



    }*/
}
