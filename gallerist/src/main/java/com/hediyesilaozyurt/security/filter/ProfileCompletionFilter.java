package com.hediyesilaozyurt.security.filter;

import com.hediyesilaozyurt.entities.authEntities.User;
import com.hediyesilaozyurt.entities.enums.UserRole;
import com.hediyesilaozyurt.entities.enums.UserStatus;
import com.hediyesilaozyurt.security.FilterResponseWriter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProfileCompletionFilter extends OncePerRequestFilter {

    private final FilterResponseWriter filterResponseWriter;

    //these endpoint below bypass the filter
    private static final List<String> WHITELIST=List.of(
        "/rest/api/auth",
            "/rest/api/customer/complete-profile"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        //take path and check out if it exist in whitelist
        String path = request.getRequestURI();
        boolean isWhiteListed = WHITELIST.stream().anyMatch((path::startsWith));

        //if it exist in whitelist, skip the filter
        if (isWhiteListed) {
            filterChain.doFilter(request, response);
            return;
        }

        //take current user from spring security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //if no auth or no login or principal is not our user
        //filter let request pass
        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof User user)) {
            filterChain.doFilter(request, response);
            return;
        }

        //only customer role is restricted
        //admin bypass
        if (user.getRole() != UserRole.CUSTOMER) {
            filterChain.doFilter(request, response);
            return;
        }

        if (user.getStatus() != UserStatus.ACTIVE_PROFILE) {
            filterResponseWriter.write(response,
                    HttpStatus.FORBIDDEN,
                    "Please complete your profile to continue");
            return;
        }

        filterChain.doFilter(request, response);
    }

}
