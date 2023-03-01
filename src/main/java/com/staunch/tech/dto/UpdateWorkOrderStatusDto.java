package com.staunch.tech.dto;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateWorkOrderStatusDto {
	
	private int orderNo;
	private String status;
	private String name;
	private int assignedTo;
	private int userId;

}
