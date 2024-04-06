package com.surya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.surya.dto.AuthenticationResponse;
import com.surya.dto.LoginRequest;
import com.surya.dto.RegisterRequest;
import com.surya.model.User;
import com.surya.service.AuthService;
import com.surya.service.JwtService;



@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController 
{

	@Autowired
    private  AuthService authService;
	@Autowired
    private  JwtService jwtService;
	@Autowired
    private  AuthenticationManager authenticationManager;

    
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody RegisterRequest registerRequest) 
    {
    	User createdUser = authService.signup(registerRequest);
        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }
    

	@PostMapping("/login")
    public AuthenticationResponse authenticateAndGetToken(@RequestBody LoginRequest loginRequest) 
	{
		 
	  Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	        
	     if (authentication.isAuthenticated()) 
	        {
	            return jwtService.generateToken(loginRequest.getUsername());
	        } 
	     else 
	        {
	            throw new UsernameNotFoundException("invalid user Credentials!");
	        }
	     
	  }

    
	  
    
}
