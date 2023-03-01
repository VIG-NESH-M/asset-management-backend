package com.staunch.tech.controller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.staunch.tech.dto.ApiResponseDto;
import com.staunch.tech.dto.TicketDto;
import com.staunch.tech.dto.TicketRespDto;
import com.staunch.tech.dto.UpdateTicketStatusDto;
import com.staunch.tech.dto.UpdateWorkOrderStatusDto;
import com.staunch.tech.dto.WorkOrderDto;
import com.staunch.tech.dto.WorkOrderRespDto;
import com.staunch.tech.service.IWorkOrderService;

@RestController
@RequestMapping("/workorder")
@CrossOrigin("*")

public class WorkOrderController {

	@Autowired
	private IWorkOrderService workorderservice;

	@Autowired
	private ObjectMapper objectMapper;

	

	@PostMapping("/save")
    public ResponseEntity<ApiResponseDto> addDetails( 
        @RequestParam("workorder") String workOrderObj,
        @Param("file") MultipartFile file) throws IOException {
    var workorderDto = objectMapper.readValue(workOrderObj, WorkOrderDto.class);
    WorkOrderRespDto data;
    System.out.println("File "+ file);
    System.out.println("File "+  file.getOriginalFilename());
    data = workorderservice.createWorkOrder(workorderDto, file);
    var response = new ApiResponseDto("1200", "Success", data);
    return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	



	@GetMapping("/list")
	public ResponseEntity<ApiResponseDto> getAllWorkOrders() {
		var response = new ApiResponseDto("1200", "Success", workorderservice.getAllWorkOrders());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

//	@GetMapping("/list/status/{status}")
//	public ResponseEntity<ApiResponseDto> getAllWorkOrdersByStatus(@PathVariable("status") String status) {
//		var response = new ApiResponseDto("1200", "Success", workorderservice.getAllWorkOrderByStatus(status));
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
	
//	@GetMapping("/{fileName}")
//	public ResponseEntity<ApiResponseDto> downloadImage(@PathVariable String fileName){
//		byte[] imageData=workorderservice.downloadImage(fileName);
//		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
//
//	}

//	@PutMapping("/status")
//	public ResponseEntity<ApiResponseDto> updateWorkOrderStatus(
//			@RequestBody UpdateWorkOrderStatusDto updateWorkOrderStatusDto) {
//		var response = new ApiResponseDto("1200", "Success",
//				workorderservice.updateWorkOrderStatus(updateWorkOrderStatusDto));
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}

//	@PutMapping("/mail/{workorderorderNo}")
//	public ResponseEntity<ApiResponseDto> updateWorkOrderAndMail(@PathVariable("workorderorderNo") int workorderorderNo,
//			@RequestBody WorkOrderDto WorkOrderDto) {
//		var response = new ApiResponseDto("1200", "Success",
//				workorderservice.updateWorkOrder(workorderorderNo, WorkOrderDto));
//		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
//	}

	@GetMapping("/list/employees/{empId}")
	public ResponseEntity<ApiResponseDto> getAllWorkOrderByOrderNo(@PathVariable("empId") int empId) {
		var response = new ApiResponseDto("1200", "Success", workorderservice.getWorkOrderByorder(empId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{WorkOrderorderNo}")
	public ResponseEntity<ApiResponseDto> getWorkOrder(@PathVariable("WorkOrderorderNo") int workorderorderNo) {
		var response = new ApiResponseDto("1200", "Success", workorderservice.getWorkOrderByorder(workorderorderNo));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@DeleteMapping("/{WorkOrderorderNo}")
    public ResponseEntity<ApiResponseDto> deleteWorkOrders(@PathVariable("WorkOrderorderNo") int workorderorderNo) {
        var response = new ApiResponseDto("1200", "Success",workorderservice.deleteWorkOrder(workorderorderNo));
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
