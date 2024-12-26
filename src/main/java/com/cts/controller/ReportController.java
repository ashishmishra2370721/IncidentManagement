package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cts.dto.ReportDto;
import com.cts.services.Implementation.ReportServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportServiceImpl reportService;

    @GetMapping("/")
    public String allAccess() {
        return "Welcome to my project!!";
    }

    @PostMapping("/save")
    public ResponseEntity<ReportDto> createReports(@RequestBody ReportDto reportDto){
        ReportDto savedReport = reportService.createReport(reportDto);
        return new ResponseEntity<>(savedReport, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ReportDto>> getAllReports(){
        List<ReportDto> allReports = reportService.getAllReports();
        return ResponseEntity.ok(allReports);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ReportDto> getReportId(@PathVariable("id") Integer reportId){
        ReportDto getRepo = reportService.getReportById(reportId);
        return ResponseEntity.ok(getRepo);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ReportDto> updateReport(@PathVariable("id") Integer employeeId,@RequestBody ReportDto updatedReportDto){
        ReportDto reportDto = reportService.updateReport(employeeId, updatedReportDto);
        return ResponseEntity.ok(reportDto);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteReport(@PathVariable("id") Integer reportId){
        reportService.deleteReport(reportId);
        return ResponseEntity.ok("Successfully deleted the employee");
    }
    
    
}