package com.example.carfix.carfixspringboot.payload.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.Set;

import static com.example.carfix.carfixspringboot.Validation.Regexp.PHONE_NUMBER;

@Getter
@Setter
public class SignUpRequest {

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    @Email
    @Size(max = 50)
    private String email;

    @NotBlank
    @Pattern(regexp = PHONE_NUMBER)
    @Size(max = 20)
    private String phoneNumber;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 6)
    private String password;

    private Set<String> role;

}
