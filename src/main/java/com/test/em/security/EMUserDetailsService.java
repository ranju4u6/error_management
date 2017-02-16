package com.test.em.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.em.common.service.CifService;
import com.test.em.entity.CifUser;

@Service("wsUserDetailsService")
public class EMUserDetailsService implements UserDetailsService {
	Logger logger = LoggerFactory.getLogger(EMUserDetailsService.class);
	
	@Autowired
	private CifService cifService;

	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
			logger.info("Validating user: {}",userName);
			System.out.println("###############################:"+userName);
			
			CifUser user = cifService.getUserDetails(userName);
			
			System.out.println("CIFUser:"+ user);
		
		return new WSUserDetails(user);
	}

}
