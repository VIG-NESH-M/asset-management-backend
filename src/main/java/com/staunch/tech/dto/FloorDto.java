package com.staunch.tech.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorDto {

	@NotNull(message = "Floor Id is Empty")
	private int id;
	
	@NotBlank(message = "Floor Name is Empty")
	private String name;
	
	private String buildingName;
	
}
