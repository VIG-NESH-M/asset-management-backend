package com.staunch.tech.service;

import com.staunch.tech.dto.EmployeeDto;
import com.staunch.tech.dto.EmployeeRespDto;
import com.staunch.tech.dto.EmployeeUpdateReqDto;
import com.staunch.tech.entity.Employee;

import java.util.List;

public interface IEmployeeService {
	EmployeeRespDto registerEmployee(EmployeeDto employeeDto);

	EmployeeRespDto updateEmployee(int empId, EmployeeUpdateReqDto employeeDto);

	EmployeeRespDto getEmployeeById(int id);

	List<EmployeeRespDto> getAllEmployees();

}
