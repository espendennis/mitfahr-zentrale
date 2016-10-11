package com.espen.ws.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(
			AuthenticationManagerBuilder auth,
			UserDetailsService userDetailsService){
		try {
			auth.
				userDetailsService(userDetailsService)
					.passwordEncoder(new BCryptPasswordEncoder());
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	protected void configure(HttpSecurity http){
		try {
			http
				.authorizeRequests()
					.antMatchers("/", "/createuser", "/about", "/api", "/resources/**", "/profil/*","/offer/*","/newuser","/usercreated" ).permitAll()
					.antMatchers("/api/**").hasRole("ADMIN")
					.anyRequest().authenticated()
					.and()
				.formLogin()
					.loginPage("/login")
					.permitAll()
					.and()
				.logout()
					.permitAll()
					.and()
				.csrf().disable()
				.formLogin().and()
				.httpBasic();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
