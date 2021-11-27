package com.example.mp3.controller;

import com.example.mp3.model.JwtResponse;
import com.example.mp3.model.User;
import com.example.mp3.service.jwt.JwtService;
import com.example.mp3.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user1 = userService.findByUsername(userDetails.getUsername());
        String token = jwtService.createTokenLogin(authentication);
        JwtResponse jwtResponse = new JwtResponse(user1.getId(), user.getUsername(), token, user1.getRoles());
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

}
