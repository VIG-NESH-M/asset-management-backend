package com.staunch.tech.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationsDto {
	
	@NotNull
	private int id;
	
	@NotBlank
	private String name;
	
	@NotNull
	private int userId;
}
