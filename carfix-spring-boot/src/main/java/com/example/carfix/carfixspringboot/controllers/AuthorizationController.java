package com.example.carfix.carfixspringboot.controllers;

import com.example.carfix.carfixspringboot.entities.ERole;
import com.example.carfix.carfixspringboot.entities.User;
import com.example.carfix.carfixspringboot.entities.Role;
import com.example.carfix.carfixspringboot.payload.request.LoginRequest;
import com.example.carfix.carfixspringboot.payload.request.SignUpRequest;
import com.example.carfix.carfixspringboot.payload.response.JwtTokenResponse;
import com.example.carfix.carfixspringboot.payload.response.MessageResponse;
import com.example.carfix.carfixspringboot.repositories.UserRepository;
import com.example.carfix.carfixspringboot.repositories.RoleRepository;
import com.example.carfix.carfixspringboot.security.json_web_token_jwt.JwtUtils;
import com.example.carfix.carfixspringboot.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/authorization")
public class AuthorizationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(new JwtTokenResponse(
                token,
                userDetails.getId(),
                userDetails.getFirstname(),
                userDetails.getLastname(),
                userDetails.getEmail(),
                userDetails.getPhoneNumber(),
                userDetails.getUsername(),
                userDetails.getPassword(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> regesterUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username already being used!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email already being used!"));
        }
        if (userRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Phone number already being used!"));
        }

        User user = new User(
                signUpRequest.getFirstname(),
                signUpRequest.getLastname(),
                signUpRequest.getEmail(),
                signUpRequest.getPhoneNumber(),
                signUpRequest.getUsername(),
                passwordEncoder.encode(signUpRequest.getPassword())
        );

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByRole(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByRole(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByRole(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByRole(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


}
