package com.cts.mapper;

import com.cts.dto.ReportDto;
import com.cts.entities.Report;
import com.cts.entities.User;

public class ReportMapper {
	public static ReportDto mapToReportDto(Report report) {
		return new ReportDto(report.getId(), report.getIrDate(), report.getIrTime(), report.getDepartment(),
				report.getIncidentType(), report.getDescription(), report.getLevel(), report.getPerson(),
				report.getDepartmentResOrSolutionPro(), report.getStatusUser());
	}

	public static Report mapToReport(ReportDto reportDto, User user) {
		Report report = new Report(reportDto.getId(), reportDto.getIrDate(), reportDto.getIrTime(),
				reportDto.getDepartment(), reportDto.getIncidentType(), reportDto.getDescription(),
				reportDto.getLevel(), reportDto.getPerson(), reportDto.getDepartmentResOrSolutionPro(),
				reportDto.getStatusUser(), user);
		return report;
	}
}