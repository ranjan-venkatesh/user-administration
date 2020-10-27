package com.ranjan.usermanagement.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ranjan.usermanagement.service.impl.UserDetailServiceImpl;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	UserDetailServiceImpl userDetailServiceImpl;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/users").permitAll()
				.antMatchers(HttpMethod.GET, "/users").hasAuthority("ADMIN").anyRequest().fullyAuthenticated().and()
				.httpBasic().and().csrf().disable().formLogin().disable();
	}

}
