package com.staunch.tech.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.staunch.tech.dto.ApiResponseDto;
import com.staunch.tech.dto.TicketDto;
import com.staunch.tech.dto.TicketRespDto;
import com.staunch.tech.dto.UpdateTicketStatusDto;
import com.staunch.tech.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/tickets")
@CrossOrigin("*")
public class TicketController {

	@Autowired
	private ITicketService ticketService;

	@Autowired
	private ObjectMapper objectMapper;

	@PostMapping("/mail")
	public ResponseEntity<ApiResponseDto> saveTicketAndMail(@RequestParam("ticketBody") String ticketDtoObj,
			@Param("file") MultipartFile file) throws JsonProcessingException {
		var ticketDto = objectMapper.readValue(ticketDtoObj, TicketDto.class);
		TicketRespDto data;
		System.out.println("File " + file);
		if (file == null) {
			System.out.println("Without Attachment");
			data = ticketService.sendMail(ticketDto);
		} else {
			System.out.println("File " + file.getOriginalFilename());
			data = ticketService.sendMailWithAttachment(ticketDto, file);
		}
		var response = new ApiResponseDto("1200", "Success", data);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@GetMapping("/list")
	public ResponseEntity<ApiResponseDto> getAllTickets() {
		var response = new ApiResponseDto("1200", "Success", ticketService.getAllTickets());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/list/status/{status}")
	public ResponseEntity<ApiResponseDto> getAllTicketsByStatus(@PathVariable("status") String status) {
		var response = new ApiResponseDto("1200", "Success", ticketService.getAllTicketsByStatus(status));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/status")
	public ResponseEntity<ApiResponseDto> updateTicketStatus(@RequestBody UpdateTicketStatusDto updateTicketStatusDto) {
		var response = new ApiResponseDto("1200", "Success", ticketService.updateTicketStatus(updateTicketStatusDto));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/mail/{ticketId}")
	public ResponseEntity<ApiResponseDto> updateTicketAndMail(@PathVariable("ticketId") String ticketId,
			@RequestBody TicketDto ticketDto) {
		var response = new ApiResponseDto("1200", "Success", ticketService.updateTicket(ticketId, ticketDto));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@GetMapping("/list/employees/{empId}")
	public ResponseEntity<ApiResponseDto> getAllTicketsByEmployeeId(@PathVariable("empId") int empId) {
		var response = new ApiResponseDto("1200", "Success", ticketService.getAllTicketsByEmployeeId(empId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{ticketId}")
	public ResponseEntity<ApiResponseDto> getTicket(@PathVariable("ticketId") String ticketId) {
		var response = new ApiResponseDto("1200", "Success", ticketService.getTicketById(ticketId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
