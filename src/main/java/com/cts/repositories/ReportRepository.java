package com.cts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entities.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {

}