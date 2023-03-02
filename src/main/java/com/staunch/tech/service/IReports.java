package com.staunch.tech.service;

import com.staunch.tech.dto.Reports3Dto;
import com.staunch.tech.dto.ReportsDto;

import java.util.List;


public interface IReports {

	
	List<ReportsDto> calculateAmountSpent();
	
	List<ReportsDto> calculatWeeklyReports();

	List<Reports3Dto> calculateRadarReport();
	
	List<Reports3Dto> calculateBubbleReport();

}
