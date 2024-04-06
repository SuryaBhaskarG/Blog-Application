package com.surya.dto;


import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.surya.model.User;



public class UserInfoUserDetails implements UserDetails
{
	 private static final long serialVersionUID = 1L;
    
	 private String username;
	 private String password;
	 
	 
	 // we are using this constructor to convert user to userUserDetails
		public UserInfoUserDetails(User user)
		{
			
			username=user.getUserName();
			password=user.getPassword();
			
		}
	


	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}
