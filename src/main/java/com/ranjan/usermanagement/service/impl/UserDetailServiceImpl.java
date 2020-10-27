package com.ranjan.usermanagement.service.impl;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ranjan.usermanagement.model.SecuredUser;
import com.ranjan.usermanagement.model.User;
import com.ranjan.usermanagement.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Resource
	UserRepository userrepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userrepository.findByEmail(email);

		return new SecuredUser(user);
	}

}
