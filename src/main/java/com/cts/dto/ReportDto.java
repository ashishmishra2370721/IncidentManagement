package com.cts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
    private int id;
    
    @NotBlank(message = "IR Date is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "IR Date must be in the format YYYY-MM-DD")
    private String irDate;
    
    @NotBlank(message = "IR Time is required")
    @Pattern(regexp = "\\d{2}:\\d{2}", message = "IR Time must be in the format HH:MM")
    private String irTime;
    
    @NotBlank(message = "Department is required")
    private String department;
    
    @NotBlank(message = "Incident Type is required")
    private String incidentType;
    
    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;
    
    @NotBlank(message = "Level is required")
    private String level;
    
    @NotBlank(message = "Person is required")
    private String person;
    
    @NotBlank(message = "Department Resolution or Solution Provided is required")
    private String departmentResOrSolutionPro;
    
    @NotBlank(message = "Status User is required")
    private String statusUser;
}