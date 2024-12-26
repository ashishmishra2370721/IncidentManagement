package com.cts.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cts.dto.ReportDto;
import com.cts.entities.Report;
import com.cts.entities.User;
import com.cts.exceptions.ResourceNotFoundException;
import com.cts.mapper.ReportMapper;
import com.cts.repositories.ReportRepository;
import com.cts.services.Implementation.ReportServiceImpl;
import com.cts.services.Implementation.UserServiceImpl;

import org.mockito.MockedStatic;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportServiceImplTest {

    @InjectMocks
    private ReportServiceImpl reportService;

    @Mock
    private ReportRepository reportRepo;

    @Mock
    private UserServiceImpl userService;

    @Test
    public void testCreateReport() {
        ReportDto reportDto = new ReportDto();
        User user = new User();
        Report report = new Report();
        Report savedReport = new Report();
        savedReport.setId(1);

        try (MockedStatic<ReportMapper> mockedMapper = mockStatic(ReportMapper.class)) {
            when(userService.getLoginUser()).thenReturn(user);
            mockedMapper.when(() -> ReportMapper.mapToReport(reportDto, user)).thenReturn(report);
            when(reportRepo.save(report)).thenReturn(savedReport);
            mockedMapper.when(() -> ReportMapper.mapToReportDto(savedReport)).thenReturn(reportDto);

            ReportDto result = reportService.createReport(reportDto);
            assertEquals(reportDto, result);
        }
    }

    @Test
    public void testGetAllReports() {
        List<Report> reports = Arrays.asList(new Report(), new Report());
        when(reportRepo.findAll()).thenReturn(reports);

        try (MockedStatic<ReportMapper> mockedMapper = mockStatic(ReportMapper.class)) {
            mockedMapper.when(() -> ReportMapper.mapToReportDto(any(Report.class))).thenReturn(new ReportDto());

            List<ReportDto> result = reportService.getAllReports();
            assertEquals(2, result.size());
        }
    }

    @Test
    public void testGetReportById() {
        Report report = new Report();
        ReportDto reportDto = new ReportDto();
        when(reportRepo.findById(1)).thenReturn(Optional.of(report));

        try (MockedStatic<ReportMapper> mockedMapper = mockStatic(ReportMapper.class)) {
            mockedMapper.when(() -> ReportMapper.mapToReportDto(report)).thenReturn(reportDto);

            ReportDto result = reportService.getReportById(1);
            assertEquals(reportDto, result);
        }
    }

    @Test
    public void testGetReportById_NotFound() {
        when(reportRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            reportService.getReportById(1);
        });
    }

    @Test
    public void testUpdateReport() {
        Report report = new Report();
        ReportDto reportDto = new ReportDto();
        when(reportRepo.findById(1)).thenReturn(Optional.of(report));
        when(reportRepo.save(report)).thenReturn(report);

        try (MockedStatic<ReportMapper> mockedMapper = mockStatic(ReportMapper.class)) {
            mockedMapper.when(() -> ReportMapper.mapToReportDto(report)).thenReturn(reportDto);

            ReportDto result = reportService.updateReport(1, reportDto);
            assertEquals(reportDto, result);
        }
    }

    @Test
    public void testDeleteReport() {
        Report report = new Report();
        when(reportRepo.findById(1)).thenReturn(Optional.of(report));
        doNothing().when(reportRepo).deleteById(1);

        reportService.deleteReport(1);
        verify(reportRepo, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteReport_NotFound() {
        when(reportRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            reportService.deleteReport(1);
        });
    }
}