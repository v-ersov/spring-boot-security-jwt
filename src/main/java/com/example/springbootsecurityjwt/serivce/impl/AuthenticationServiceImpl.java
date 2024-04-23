package com.example.springbootsecurityjwt.serivce.impl;

import com.example.springbootsecurityjwt.dto.request.SignInRequest;
import com.example.springbootsecurityjwt.dto.request.SignUpRequest;
import com.example.springbootsecurityjwt.dto.response.JwtAuthenticationResponse;
import com.example.springbootsecurityjwt.model.Role;
import com.example.springbootsecurityjwt.model.User;
import com.example.springbootsecurityjwt.serivce.AuthenticationService;
import com.example.springbootsecurityjwt.serivce.JwtService;
import com.example.springbootsecurityjwt.serivce.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

}
