package org.springframework.boot.api.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.domain.constant.AppConstants;
import org.springframework.boot.props.UserProps;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final UserProps userProps;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(AppConstants.AUTHORIZATION);
        String[] securePaths = {
                AppConstants.API + AppConstants.API,
        };

        boolean isSecurePath = false;
        for (String securePath : securePaths) {
            if (request.getServletPath().startsWith(securePath)) {
                isSecurePath = true;
                break;
            }
        }

        if (!isSecurePath || isValidToken(authorization)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized access");
            response.getWriter().flush();
        }
    }

    private boolean isValidToken(String token) {
        return userProps.getCustom().equals(token);
    }
}
