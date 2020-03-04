package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.model.User;
import com.repository.*;


@Service
public class userDetails implements UserDetailsService {

	@Autowired(required=true)
	private UserRepository repo;

	@Autowired
	PasswordEncoder PasswordEncoder;
	
	@Override
	public /*org.springframework.security.core.userdetails.UserDetails*/ UserPrincipal loadUserByUsername(String uname) throws UsernameNotFoundException 
	{
		
	User user = repo.findByUsername(uname);
	
	
	if (user==null)
	{
		System.out.println("not found ");
	throw new UsernameNotFoundException("404");

	}
	System.out.println(user.getUsername()+" found ");
	System.out.println(""+user.getPassword()+ "  encoded form  "+PasswordEncoder.encode(user.getPassword()));
	return new UserPrincipal(user);
	}
}