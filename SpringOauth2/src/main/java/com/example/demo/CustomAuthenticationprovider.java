package com.example.demo;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.repository.UserRepository;

@Component
public class CustomAuthenticationprovider
		implements org.springframework.security.authentication.AuthenticationProvider {

	@Autowired
	UserRepository userRepository;

	@Autowired
	userDetails userDetails;

	UserPrincipal UserPrincipal;

	@Autowired
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public Authentication authenticate(Authentication authentication) {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		com.model.User user = userRepository.findByUsername(username);
		UserPrincipal = userDetails.loadUserByUsername(username);
		// UserPrincipal user=userDetails.loadUserByUsername(username);

		System.out.println("User repository object  " + UserPrincipal.getUsername());
		System.out.println(username);
		/*
		 * if (user == null || !user.getUsername().equalsIgnoreCase(username)) { throw
		 * new BadCredentialsException("Username not found."); }
		 */
		System.out.println(password);
		System.out.println("UserPrincipal.getPassword()  " + UserPrincipal.getPassword());
		System.out.println("passwordEncoder().encode(password) " + passwordEncoder().encode(password));
		if (!password.equals(UserPrincipal.getPassword())) {
			throw new BadCredentialsException("Wrong password.........");
		}

		/*
		 * if (!password.equals(user.getPassword())) { throw new
		 * BadCredentialsException("Wrong password."); }
		 */

		Collection<? extends GrantedAuthority> authorities = UserPrincipal.getAuthorities();
		System.out.println("ROLE_-->>" + authorities);
		System.out.println("" + new UsernamePasswordAuthenticationToken(UserPrincipal, password, authorities));
		return new UsernamePasswordAuthenticationToken(UserPrincipal, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
