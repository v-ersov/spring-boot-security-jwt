package com.example.springbootsecurityjwt.serivce;

import com.example.springbootsecurityjwt.dto.request.SignInRequest;
import com.example.springbootsecurityjwt.dto.request.SignUpRequest;
import com.example.springbootsecurityjwt.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);
}
