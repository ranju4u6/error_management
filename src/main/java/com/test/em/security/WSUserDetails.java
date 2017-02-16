package com.test.em.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.test.em.entity.CifUser;

public class WSUserDetails implements UserDetails {
	
	CifUser cifUser = null;
	
	public WSUserDetails(CifUser cifuser){
		this.cifUser = cifuser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		if(cifUser == null)
			return null;
		else
			return cifUser.getPwd();
	}

	@Override
	public String getUsername() {
		if(cifUser == null)
			return null;
		else
			return cifUser.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
