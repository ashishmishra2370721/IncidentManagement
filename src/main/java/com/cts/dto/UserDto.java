package com.cts.dto;

import java.util.List;

import com.cts.entities.Report;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
	
	@NotBlank(message = "Username is required")
    @Size(min = 3, max = 14, message = "Username must be between 3 and 14 characters")
    private String username;
	
	@NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
	
	@NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;
    
    @NotBlank(message = "Address is required")
    private String address;
    
    @NotBlank(message = "Pin code is required")
    @Pattern(regexp = "\\d{6}", message = "Pin code must be 6 digits")
    private String pinCode;
    
    @NotBlank(message = "City is required")
    private String city;
    
    @NotBlank(message = "Country is required")
    private String country;
    
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    
    @NotBlank(message = "Role is required")
    private String role;
    
    private List<Report> reports;
}
