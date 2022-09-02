package com.training.controller;

import com.training.Dto.AuthenticationRequest;
import com.training.Dto.AuthenticationResponse;
import com.training.exceptions.InvalidUserCredentialsException;
import com.training.repo.UserRepo;
import com.training.service.UserService;
import com.training.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/signIn")
public class Authernticate {

    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/user")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest userRequest) {
        org.springframework.security.core.Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword());
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
        	
        	throw new InvalidUserCredentialsException("Invalid Credentials");
        }
        User user = (User) authentication.getPrincipal();
        com.training.model.User user1=userService.getUser(userRequest.getUsername());

        String token=jwtUtil.generateToken(authentication);
        AuthenticationResponse userResponse = new AuthenticationResponse();
        userResponse.setToken(token);
        userResponse.setRole(user1.getRole());
        userResponse.setId(user1.getUser_id());
        return new ResponseEntity<AuthenticationResponse>(userResponse, HttpStatus.OK);
    }
    


}

