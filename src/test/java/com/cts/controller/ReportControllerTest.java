package com.cts.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.controller.ReportController;
import com.cts.dto.ReportDto;
import com.cts.services.Implementation.ReportServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportControllerTest {

    @InjectMocks
    private ReportController reportController;

    @Mock
    private ReportServiceImpl reportService;

    @Test
    public void testAllAccess() {
        String response = reportController.allAccess();
        assertEquals("Welcome to my project!!", response);
    }

    @Test
    public void testCreateReports() {
        ReportDto reportDto = new ReportDto();
        ReportDto savedReport = new ReportDto();
        when(reportService.createReport(reportDto)).thenReturn(savedReport);

        ResponseEntity<ReportDto> response = reportController.createReports(reportDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedReport, response.getBody());
    }

    @Test
    public void testGetAllReports() {
        List<ReportDto> reports = Arrays.asList(new ReportDto(), new ReportDto());
        when(reportService.getAllReports()).thenReturn(reports);

        ResponseEntity<List<ReportDto>> response = reportController.getAllReports();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reports, response.getBody());
    }

    @Test
    public void testGetReportId() {
        ReportDto reportDto = new ReportDto();
        when(reportService.getReportById(1)).thenReturn(reportDto);

        ResponseEntity<ReportDto> response = reportController.getReportId(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reportDto, response.getBody());
    }

    @Test
    public void testUpdateReport() {
        ReportDto reportDto = new ReportDto();
        when(reportService.updateReport(1, reportDto)).thenReturn(reportDto);

        ResponseEntity<ReportDto> response = reportController.updateReport(1, reportDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reportDto, response.getBody());
    }

    @Test
    public void testDeleteReport() {
        doNothing().when(reportService).deleteReport(1);

        ResponseEntity<String> response = reportController.deleteReport(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully deleted the employee", response.getBody());
    }
}