package com.example.carfix.carfixspringboot.security.json_web_token_jwt;

import com.example.carfix.carfixspringboot.security.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {


        try {
            String jwtToken = parseJwtToken(httpServletRequest);

            if (jwtToken != null && jwtUtils.validateJwtToken(jwtToken)) {

                String username = jwtUtils.getUsernameFromJwtToken(jwtToken);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception exception) {
            logger.error("Can not set user Authentication: {}", exception.getMessage());
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String parseJwtToken(HttpServletRequest httpServletRequest) {

        String authenticationHeader = httpServletRequest.getHeader("Authorization");

        if (StringUtils.hasText(authenticationHeader) && authenticationHeader.startsWith("Bearer ")) {

            return authenticationHeader.substring(7, authenticationHeader.length());

        } else {

            return null;
        }
    }
}
