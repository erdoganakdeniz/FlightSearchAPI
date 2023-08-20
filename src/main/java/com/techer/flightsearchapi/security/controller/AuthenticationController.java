package com.techer.flightsearchapi.security.controller;

import com.techer.flightsearchapi.security.entity.ApplicationUser;
import com.techer.flightsearchapi.security.entity.LoginResponseDTO;
import com.techer.flightsearchapi.security.entity.RegistrationDTO;
import com.techer.flightsearchapi.security.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("*")
@Tag(name = "Authentication API")
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body) {
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body) {
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}