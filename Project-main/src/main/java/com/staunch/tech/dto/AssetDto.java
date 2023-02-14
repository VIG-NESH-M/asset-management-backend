package com.staunch.tech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetDto {
	private int id;
	@NotBlank(message = "Name is Empty")
	private String name;
	@NotBlank(message = "Description is Empty")
	private String description;
	@NotNull(message = "Price is Empty")
	private float price;
	@NotBlank(message = "Facility Code is Empty")
	private String facilityCode;
	@NotNull(message = "user id is Empty!")
	private int userId;
}
