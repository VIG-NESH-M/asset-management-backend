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
public class EmployeeUpdateReqDto {

    @NotNull
    private  int id;
    private  String name;
    private  String email;
    private  String username;
    private  Long phoneNumber;
    private String department;
    private String designation;
    private Set<String> roles;
    private String address;
    private String location;
    private String usertype;
    private String resourceplanner;
    private String password;
    @NotNull(message="user id Empty")
    private int userId;
}
