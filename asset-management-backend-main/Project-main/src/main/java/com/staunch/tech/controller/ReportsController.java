package com.staunch.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.staunch.tech.dto.ApiResponseDto;
import com.staunch.tech.service.IReports;
import com.staunch.tech.service.ITicketService;
import java.util.List;


import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/reports")
@CrossOrigin("*")
public class ReportsController {
	
	@Autowired
	private IReports reportServices;
	
	@Autowired
	private ITicketService ticketServices;

	
	@GetMapping("/amount-spent")
	public ResponseEntity<ApiResponseDto> viewCalculateAmountSpent(){
		var response = new ApiResponseDto("1200", "Success", reportServices.calculateAmountSpent());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/timeline")
	public ResponseEntity<ApiResponseDto> viewTimeline(){
		var response = new ApiResponseDto("1200", "Success", reportServices.calculatWeeklyReports());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/radar")
	public ResponseEntity<ApiResponseDto> viewRadar(){
		var response = new ApiResponseDto("1200", "Success", reportServices.calculateRadarReport());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/values")
	public ResponseEntity<ApiResponseDto> getvalues(@RequestParam List<String> listId){
		var response = new ApiResponseDto("1200", "Success", ticketServices.getAllTicketsById(listId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
