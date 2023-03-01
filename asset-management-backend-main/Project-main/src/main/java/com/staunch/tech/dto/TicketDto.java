package com.staunch.tech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
	private String uuid;
	@NotBlank(message = "Title is Empty")
	private String title;
	@NotBlank(message = "Description is Empty")
	private String description;
	@NotBlank(message = "Category is Empty")
	private String category;
	private String status;
	@NotNull(message = "Employee Id is Empty")
	private int employeeId;
	@NotBlank(message = "Issue Type is Empty")
	private String issueType;
	
	private String assetId;
	
	private long totalAmountSpent;

	@NotNull(message = "user id is Empty!")
	private int userId;
	@NotNull(message = "Expected Completion Change is Empty!")
	private long expectedCompletionTime;
}
