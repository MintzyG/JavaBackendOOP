package com.demo.finance.filters;

import com.demo.finance.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class AuthFilters extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "Authorization header is missing");
            return;
        }

        String[] authHeaderArray = authHeader.split("Bearer ");
        if (authHeaderArray.length <= 1 || authHeaderArray[1] == null) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be Bearer [token]");
            return;
        }

        String token = authHeaderArray[1];
        try {
            Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY)
                    .parseClaimsJws(token).getBody();
            request.setAttribute("user_id", Integer.parseInt(claims.get("user_id").toString()));
        } catch (Exception e) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "Invalid/Expired token");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
