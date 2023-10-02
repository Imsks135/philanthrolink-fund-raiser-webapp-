package com.stackroute.AuthenticationService.services;

import com.stackroute.AuthenticationService.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stackroute.AuthenticationService.model.User;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepo repository;


    
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		
		User user=repository.findByUserEmail(userEmail);
        return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPassword(), (Collection<? extends GrantedAuthority>) new ArrayList());
	}
}
