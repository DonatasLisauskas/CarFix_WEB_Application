package com.example.carfix.carfixspringboot.payload.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class JwtTokenResponse {
    
    private String accessToken = "Bearer";

    @NonNull
    private String token;

    @NonNull
    private Long id;

    @NonNull
    private String firstname;

    @NonNull
    private String lastname;

    @NonNull
    private String email;

    @NonNull
    private String phoneNumber;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private List<String> role;
}
