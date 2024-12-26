package com.cts.services.Interface;

import java.util.List;

import com.cts.dto.ReportDto;

public interface ReportService {
	public ReportDto createReport(ReportDto reportDto);
	public List<ReportDto> getAllReports();
	public ReportDto getReportById(Integer repoId);
	public ReportDto updateReport(Integer reportId,ReportDto updatedReportDto);
	public void deleteReport(Integer reportId);
}
