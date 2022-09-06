package com.training.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.training.service.UserService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	UserService userDetailService;
	
	  @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/users/signUp", "/signIn/user", "/signIn/owner","/**");
	    }

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	    }

	    @Bean
	    @Override
	    protected AuthenticationManager authenticationManager() throws Exception {
	        return super.authenticationManager();
	    }

	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception {
	        httpSecurity.cors();
	        httpSecurity.csrf().disable().httpBasic().and().authorizeRequests().antMatchers("/login", "/signup"
	                        , "/questions/*", "/owner/**","/answers/*/"
	                ).permitAll()
	                .anyRequest().authenticated()
	                .and().sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	    }

}
