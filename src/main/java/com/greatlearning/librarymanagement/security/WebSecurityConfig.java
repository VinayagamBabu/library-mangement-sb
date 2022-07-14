package com.greatlearning.librarymanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.greatlearning.librarymanagement.service.MyUserDetailsService;
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public UserDetailsService getUserDetailsService() {
		return new MyUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(getUserDetailsService());
		auth.setPasswordEncoder(getBCryptPasswordEncoder());
		return auth;
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder mgr) {
		mgr.authenticationProvider(getAuthenticationProvider());
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.antMatchers("/","/books/save","/books/list","/books/showFormForAdd","/books/search").hasAnyAuthority("USER","ADMIN")
		.antMatchers("/books/showFormForUpdate","/books/delete").hasAuthority("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginProcessingUrl("/login").successForwardUrl("/books/list").permitAll()
		.and()
		.logout().logoutSuccessUrl("/login").permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/books/403")
		.and()
		.cors()
		.and()
		.csrf().disable();
	}

}
