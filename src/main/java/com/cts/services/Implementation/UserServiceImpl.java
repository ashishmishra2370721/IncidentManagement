package com.cts.services.Implementation;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.dto.UserDto;
import com.cts.entities.User;
import com.cts.repositories.UserRepository;
import com.cts.services.Interface.UserService;


@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> saveUser(UserDto userDto) {
    	
    	log.info("Attempting to save user with username: {}", userDto.getUsername());
    	
    	if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
    		
    		log.warn("Username is already taken");
    		
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        try{
            String ps = passwordEncoder.encode(userDto.getPassword());
            User user = User.builder().email(userDto.getEmail()).city(userDto.getCity()).country(userDto.getCountry())
                    .address(userDto.getAddress()).email(userDto.getEmail())
                    .password(ps).phoneNumber(userDto.getPhoneNumber())
                    .username(userDto.getUsername()).pinCode(userDto.getPinCode()).role(userDto.getRole()).build();
            userRepository.save(user);
            
            log.info("User registered successfully with username: {}", userDto.getUsername());
            
            return ResponseEntity.ok("User registered successfully");
        }catch (Exception e){
        	
        	log.error("Error occurred while saving user: {}", e.getMessage());
        	
            throw e;
        }
    }
    
    public User getLoginUser() {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
        String  email = authentication.getName();
        
        log.info("Fetching logged-in user with email: {}", email);
        
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User Not found"));
    }

}







