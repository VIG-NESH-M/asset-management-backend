package com.staunch.tech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KRResponseDto {

	private int id;
	
	private String name;
	
	private String asset_name;
	
	private int upload_by;
	
	private long upload_time;
	
	
}
