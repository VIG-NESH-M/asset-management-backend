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
public class AssetDto {
	private int id;
	@NotBlank(message = "Name is Empty")
	private String name;
	@NotBlank(message = "Code is Empty")
    private String code;
	@NotNull(message = "Serial no is Empty")
    private int serialNo;
    @Column(name ="description")
    @NotBlank(message = "Description is Empty")
	private String description;
    @NotBlank(message = "Facility Code is Empty")
	private String facilityCode;
	@NotBlank(message = "Category is Empty")
	private String category;
	@NotBlank(message = "Department is Empty")
	private String department;
	@NotBlank(message = "Department is Empty")
	private String subAsset;
	@NotBlank(message = "SubAsset is Empty")
	private String supplier;
	@NotBlank(message = "Priority is Empty")
	private String priority;
	@NotBlank(message = "Model is Empty")
	private String model;
	@NotNull(message = "Price is Empty")
	private float price;
	@NotNull(message = "user id is Empty!")
	private int userId;
}
