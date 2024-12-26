package com.cts.services.Interface;


import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.cts.dto.UserDto;
import com.cts.entities.User;

public interface UserService {

	ResponseEntity<?> saveUser(UserDto userDto);
	 	
}
