package com.hediyesilaozyurt.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    @Lazy
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Bearer fndskjfjndsk(token)
        final String authHeader;

        authHeader=request.getHeader("Authorization");
        //check out if header is null or is not a Bearer
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        final String token=authHeader.substring(7);
        final String username;

        try {
            username= jwtService.extractUsername(token);
            //check out if username exist or authenticated before
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                //get userdetails from db
                UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);

                if(userDetails!=null && jwtService.isTokenValid(token,userDetails)){
                    UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails,
                            null,userDetails.getAuthorities());

                    authentication.setDetails(userDetails);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("token is expired");
        }
        catch (UsernameNotFoundException e) {
            throw new RuntimeException("user not found");
        } catch (Exception e) {
            throw new RuntimeException("unauthorized access");
        }

        filterChain.doFilter(request,response);
    }
}
