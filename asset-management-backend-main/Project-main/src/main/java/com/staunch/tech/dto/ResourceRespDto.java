package com.staunch.tech.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceRespDto {
	private int resourceid;
	private String resourceName;
	private String resourcetype;
	private Date startDate;
	private Date endDate;
	private String availability;
}
