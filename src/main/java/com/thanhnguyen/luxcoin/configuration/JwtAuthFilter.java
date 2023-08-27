package com.thanhnguyen.luxcoin.configuration;

import com.thanhnguyen.luxcoin.model.entity.User;
import com.thanhnguyen.luxcoin.repository.IUserRepository;
import com.thanhnguyen.luxcoin.service.IUserService;
import com.thanhnguyen.luxcoin.service.impl.UserDetailsServiceImpl;
import com.thanhnguyen.luxcoin.service.impl.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userService;

    private final JwtUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String authHeader = request.getHeader(AUTHORIZATION);
        String token = null;
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("luxToken")) {
                    token = cookie.getValue();
                }
            }
        }
        try {
            final String username;
            final String jwtToken;
            if ((authHeader == null && token == null) || Objects.equals(token, "null") || Objects.equals(token, "undefined")) {
                filterChain.doFilter(request, response);
                return;
            }
            if (token != null) {
                jwtToken = token;
            } else {
                if (authHeader.startsWith("Bearer")) {
                    jwtToken = authHeader.substring(7);
                } else {
                    filterChain.doFilter(request, response);
                    return;
                }
            }

            username = jwtUtils.extractUsername(jwtToken);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userService.loadUserByUsername(username);
                if (jwtUtils.isTokenValid(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        catch (ExpiredJwtException e){
            e.printStackTrace();
        }
        filterChain.doFilter(request,response);
    }
}
