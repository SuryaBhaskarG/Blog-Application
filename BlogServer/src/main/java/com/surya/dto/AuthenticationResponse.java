package com.surya.dto;

//import lombok.AllArgsConstructor;



//@AllArgsConstructor
public class AuthenticationResponse 
{
	private String authenticationToken;
	private String username;

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public AuthenticationResponse(String authenticationToken, String username) {
		super();
		this.authenticationToken = authenticationToken;
		this.username = username;
	}

	public AuthenticationResponse() {
		super();
	}

}