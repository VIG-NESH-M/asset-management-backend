package com.staunch.tech.dto;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class WorkOrderDto {
	private int orderNo;
	private String status;
	@NotBlank(message = "name is Empty")
	private String name;
	@NotBlank(message = "emailId is Empty")
	private String emailId;
	@NotNull(message = "phone is Empty")
	private long phoneNumber;
	@NotBlank(message = "Description is Empty")
	private String description;
	@NotBlank(message = "work_subject is Empty")
    private String workSubject;
	@NotBlank(message = "task_details is Empty")
    private String taskDetails;
    @JsonFormat(pattern = "yyyy-mm-dd") 
    private Date date;
    private int workOrderCost;
//    private byte[] data;
	@NotNull
	private int employeeId;	
	@NotNull(message = "Expected Completion Change is Empty!")
    private long expectedCompletionTime;
	
	




	
	
	

}
