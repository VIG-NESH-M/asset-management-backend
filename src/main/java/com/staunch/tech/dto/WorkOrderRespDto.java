package com.staunch.tech.dto;

import java.util.Date;

import com.staunch.tech.entity.Employee;
import com.staunch.tech.entity.WorkOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class WorkOrderRespDto {
	
	private int orderNo;
	private String status;
	private String name;
	private String emailId;
	private Employee employeeId;
	private long phoneNumber;
	private String description;
	private String workSubject;
	private String taskDetails;
	private Date date;
	private int workOrderCost;
	private byte[] data;
	private String createdBy;
    private long createdTime;
    private String updatedBy;
    private long updatedTime;
    private long timeTaken;
 
    private boolean expand;
    private long expectedCompletionTime;


}
