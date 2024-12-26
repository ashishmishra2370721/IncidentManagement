package com.cts.services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dto.ReportDto;
import com.cts.entities.Report;
import com.cts.entities.User;
import com.cts.exceptions.ResourceNotFoundException;
import com.cts.mapper.ReportMapper;
import com.cts.repositories.ReportRepository;
import com.cts.services.Interface.ReportService;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService{

    @Autowired
    private ReportRepository reportRepo;
    
    @Autowired
    private UserServiceImpl userService;

    public ReportDto createReport(ReportDto reportDto){
    	
    	log.info("Creating a new report");
    	
    	User user = userService.getLoginUser();
        Report report = ReportMapper.mapToReport(reportDto, user);
        Report savedReport = reportRepo.save(report);
        
        log.info("Report created successfully with ID: {}", savedReport.getId());
        
        return ReportMapper.mapToReportDto(savedReport);
    }

    public List<ReportDto> getAllReports() {
    	
    	log.info("Fetching all reports");
    	
        List<Report> reports = reportRepo.findAll();
        
        log.info("Total reports found: {}", reports.size());
        
        return reports.stream().map((e) ->  ReportMapper.mapToReportDto(e)).collect(Collectors.toList());
    }

    public ReportDto getReportById(Integer reportId) {
    	
    	log.info("Fetching report with ID: {}", reportId);
    	
        Report report = reportRepo.findById(reportId)
                .orElseThrow(() -> 
                 new ResourceNotFoundException("Report is not exists with the given id : " + reportId));
        
        log.info("Report found with ID: {}", reportId);
        
        return ReportMapper.mapToReportDto(report);
    }

    public ReportDto updateReport(Integer reportId,ReportDto updatedReportDto) {
    	
    	log.info("Updating report with ID: {}", reportId);
    	
        Report report =  reportRepo.findById(reportId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Report is not exists with the given id : " + reportId));

        report.setIrDate(updatedReportDto.getIrDate());
        report.setIrTime(updatedReportDto.getIrTime());
        report.setDepartment(updatedReportDto.getDepartment());
        report.setIncidentType(updatedReportDto.getIncidentType());
        report.setLevel(updatedReportDto.getLevel());
        report.setPerson(updatedReportDto.getPerson());
        report.setDescription(updatedReportDto.getDescription());
        report.setStatusUser(updatedReportDto.getStatusUser());
        report.setDepartmentResOrSolutionPro(updatedReportDto.getDepartmentResOrSolutionPro());

        Report updatedReport = reportRepo.save(report);
        
        log.info("Report updated successfully with ID: {}", updatedReport.getId());

        return ReportMapper.mapToReportDto(updatedReport);
    }

    public void deleteReport(Integer reportId) {
    	
    	log.info("Deleting report with ID: {}", reportId);
    	
        Report report =  reportRepo.findById(reportId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Report is not exists with the given id : " + reportId));
        System.out.println(report);
        reportRepo.deleteById(reportId);
        
        log.info("Report deleted successfully with ID: {}", reportId);
    }
}