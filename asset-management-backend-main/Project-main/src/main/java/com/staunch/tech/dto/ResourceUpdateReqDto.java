package com.staunch.tech.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceUpdateReqDto {
	private int resourceid;
	private String resourceName;
	private Date endDate;
}
