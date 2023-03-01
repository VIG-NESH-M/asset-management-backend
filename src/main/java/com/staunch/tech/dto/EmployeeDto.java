package com.staunch.tech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
	private int id;
	@NotBlank(message = "Name is Empty")
	private String name;
	@NotBlank(message = "Email is Empty")
	private String email;
	@NotNull(message = "PhoneNumber is Null")
	private long phoneNumber;
	@NotBlank(message = "Department is Empty")
	private String department;
	@NotBlank(message = "Designation is Empty")
	private String designation;
	@NotBlank(message = "Username is Empty")
	private String username;
	@NotBlank(message = "Password is Empty")
	private String password;
	@NotBlank(message = "address is Empty")
	private String address;
	@NotBlank(message = "location is Empty")
	private String location;
	@NotNull(message = "Roles is Empty")
	private Set<String> roles;
}
