package com.staunch.tech.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDto {

	@NotNull(message = "WorkerOrderId is empty")
	private int workOrderId;
	private int resourceId;
	@NotBlank(message = "employeeName is empty")
	private String resourceName;
	@NotBlank(message = "empty")
	private String resourceType;
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date startDate;
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date endDate;
	@NotNull(message = "Availability is empty")
	private String availability;
	@NotNull(message = "User_Id is empty")
	private int userId;
	@NotNull(message = "INventory_Id is empty")
	private int inventoryId;

}
