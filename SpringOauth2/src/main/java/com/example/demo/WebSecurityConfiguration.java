package com.example.demo;

import org.omg.CORBA.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	/*
	 * @Autowired private PasswordEncoder passwordEncoder;
	 */

	@Autowired
	UserDetailsService userDetailsService;

	/*
	 * @Bean public AuthenticationProvider authProvider() {
	 * 
	 * 
	 * 
	 * 
	 * return null; }
	 */

	@Autowired
	private CustomAuthenticationprovider authProvider;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this.authProvider);

	}

	/*
	 * userdetails{ //PasswordEncoder encoder =
	 * PasswordEncoderFactories.createDelegatingPasswordEncoder();
	 * 
	 * UserDetails user =
	 * User.builder().username("user").password(passwordEncoder.encode("secret")).
	 * roles("USER") .build(); UserDetails userAdmin =
	 * User.builder().username("admin").password(passwordEncoder.encode("secret"))
	 * .roles("ADMIN").build(); return new InMemoryUserDetailsManager(user,
	 * userAdmin);
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	            .logout().invalidateHttpSession(true).clearAuthentication(true)
	            .logoutSuccessUrl("http://localhost:8080/api/logout");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
