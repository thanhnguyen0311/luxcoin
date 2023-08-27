package com.thanhnguyen.luxcoin.controller.rest;

import com.thanhnguyen.luxcoin.configuration.JwtUtils;
import com.thanhnguyen.luxcoin.model.dto.userdto.request.UserLoginDto;
import com.thanhnguyen.luxcoin.model.dto.userdto.response.AuthenticatedResponse;
import com.thanhnguyen.luxcoin.model.entity.User;
import com.thanhnguyen.luxcoin.service.IUserService;
import com.thanhnguyen.luxcoin.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;

import org.apache.hc.client5.http.impl.cookie.BasicClientCookie;
import org.apache.hc.client5.http.protocol.HttpClientContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthenticationManager authenticationManager;

    @Autowired
    private IUserService<User> userService;

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) throws IOException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword())
        );
        final UserDetails user = userDetailsService.loadUserByUsername(userLoginDto.getUsername());
        if (user != null) {
            User user1 = userService.findByUsername(userLoginDto.getUsername());
            user1.setLastSeen(LocalDateTime.now());
            userService.save(user1);
            String token = jwtUtils.generateToken(user);
            Cookie tokenCookie = new Cookie("token",token);
//            tokenCookie.setExpiryDate(Date.from(new Date().toInstant().plusSeconds(500000)));
            tokenCookie.setPath("/");
            tokenCookie.setHttpOnly(true);
            tokenCookie.setSecure(true);
            tokenCookie.setMaxAge(60 * 60);
            response.addCookie(tokenCookie);
            AuthenticatedResponse authenticatedResponse = new AuthenticatedResponse(token,user.getAuthorities().toString());
            return ResponseEntity.ok(authenticatedResponse);
        } else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}

