package com.example.carfix.carfixspringboot.service;

import com.example.carfix.carfixspringboot.entities.User;
import com.example.carfix.carfixspringboot.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByLogin(String login);

    User save(UserRegistrationDto registration);
}
