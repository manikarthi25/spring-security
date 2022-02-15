package com.spring.security.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityDemo extends WebSecurityConfigurerAdapter  {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.withDefaultSchema()
		.withUser("user")
		.password(encoder.encode("user"))
		.roles("USER")
		.and()
		.withUser("admin")
		.password(encoder.encode("admin"))
		.roles("ADMIN");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user").hasAnyRole("ADMIN","USER")
		.antMatchers("/").permitAll()
		.antMatchers("/h2-console/**").permitAll()
		.and()
		.formLogin();
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
	}
	
	
	

}
