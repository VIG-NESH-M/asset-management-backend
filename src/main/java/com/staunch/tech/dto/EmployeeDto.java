package com.staunch.tech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private  int id;
    @NotBlank(message = "Name is Empty")
    private  String name;
    @NotBlank(message = "Email is Empty")
    private  String email;
    @NotBlank(message = "Username is Empty")
    private  String username;
    @NotNull(message = "PhoneNumber is Null")
    private  long phoneNumber;
    @NotNull(message = "Department is Null")
    private  String department;
    @NotBlank(message = "Designation is Empty")
    private  String designation;
    @NotNull(message = "Roles is Empty")
    private Set<String> roles;
    @NotBlank(message = "Address is Empty")
    private  String address;
    @NotNull(message = "Location is Null")
    private  String location;
    @NotBlank(message = "Usertype is Empty")
    private  String usertype;
    @NotBlank(message = "resource planner is Empty")
    private  String resourceplanner;
    @NotBlank(message = "Password is Empty")
    private  String password;
   
   
}
