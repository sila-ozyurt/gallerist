package com.hediyesilaozyurt.services.authenticationService.impl;

import com.hediyesilaozyurt.dto.authDto.AuthRequest;
import com.hediyesilaozyurt.dto.authDto.AuthResponse;
import com.hediyesilaozyurt.entities.authEntities.RefreshToken;
import com.hediyesilaozyurt.entities.authEntities.User;
import com.hediyesilaozyurt.entities.enums.UserRole;
import com.hediyesilaozyurt.exception.BaseException;
import com.hediyesilaozyurt.exception.ErrorMessage;
import com.hediyesilaozyurt.exception.MessageType;
import com.hediyesilaozyurt.security.jwt.JwtService;
import com.hediyesilaozyurt.repository.UserRepository;
import com.hediyesilaozyurt.services.authenticationService.IAuthenticationService;
import com.hediyesilaozyurt.services.authenticationService.IRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IRefreshTokenService refreshTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private User createUser(AuthRequest request,UserRole role){
        User user=new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);

        return user;
    }

    private AuthResponse buildAuthResponse(User user,String message){
        String accessToken= jwtService.generateToken(user);
        RefreshToken refreshToken=refreshTokenService.createRefreshToken(user.getId());

        return new AuthResponse(
                user.getUsername(),
                user.getRole(),
                accessToken,
                refreshToken.getRefreshToken(),
                message);
    }

    @Override
    public AuthResponse register(AuthRequest request,UserRole role) {
       //username control
        if(userRepository.existsByUsername(request.getUsername())){
            throw new BaseException(new ErrorMessage(
                    "usrname already taken",
                    MessageType.DUPLICATE_ENTRY
            ));
        }


        User user=this.createUser(request,role);
        userRepository.save(user);


        return buildAuthResponse(user,"registeration is successfull");
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        //if authentication fails, throws exception automatically in globalexceptionhandler . No need to catch it
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        //get user from authentication
 /*               Authentication
         ├── principal (user)
         ├── credentials (password)
         ├── authorities (roles)*/
        User user=(User) authentication.getPrincipal();

        return buildAuthResponse(user, "user entrance is successfull");


    }
}
