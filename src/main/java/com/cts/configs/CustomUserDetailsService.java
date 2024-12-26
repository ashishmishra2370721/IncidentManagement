package com.cts.configs;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.entities.User;
import com.cts.exceptions.NotFoundException;
import com.cts.repositories.UserRepository;
 
 
@Service
public class CustomUserDetailsService implements UserDetailsService {
 
	@Autowired
	private UserRepository userRepository;
 
	@Override
 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 
		User user = userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("User/ Email Not found"));
		return AuthUser.builder().user(user).build();
 
	}
}

