package com.staunch.tech.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {
	
	private int id;
	
	@NotBlank
	private String name;
	
	@NotNull
	private int code;
	
	@NotBlank
	private String description;
	
	@NotNull
	private int quantity;
	
	@NotNull
	private float price;
	
	@NotNull(message = "user id is Empty!")
	private int userId;
}
