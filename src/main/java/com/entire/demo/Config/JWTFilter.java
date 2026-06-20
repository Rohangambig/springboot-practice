package com.entire.demo.Config;

import com.entire.demo.service.CustomDetailService;
import com.entire.demo.utili.JWTUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    CustomDetailService customDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String BearerToken = request.getHeader("Authorization");

        if(request.getRequestURI().equals("/login") || request.getRequestURI().equals("/register")) {
            filterChain.doFilter(request,response);
            return;
        }

        if(BearerToken == null || !BearerToken.startsWith("Bearer ")) {
            filterChain.doFilter(request,response);
            return;
        }

        String token = BearerToken.substring(7);
        if(token.isEmpty()){
            filterChain.doFilter(request,response);
            return;
        }


        Claims userClaims = jwtUtil.validateToken(token);

        if(!jwtUtil.checkExpiration(userClaims.getExpiration())){
            filterChain.doFilter(request,response);
            return;
        }

        String user = userClaims.getSubject();
        if(user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            create a authentication token
            UserDetails userDetails = customDetailService.loadUserByUsername(user);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request,response);

    }
}
