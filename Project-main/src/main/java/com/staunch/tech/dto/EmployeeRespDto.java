package com.staunch.tech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRespDto {
    private  int id;
    private  String name;
    private  String email;
    private  long phoneNumber;
    private  String department;
    private  String designation;
    private  String username;
    private Set<String> roles;
}
