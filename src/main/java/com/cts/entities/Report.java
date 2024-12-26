package com.cts.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String irDate;
    
    private String irTime;

    private String department;

    private String incidentType;

    private String description;

    private String level;

    private String person;

    private String departmentResOrSolutionPro;

    private String statusUser;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;
}