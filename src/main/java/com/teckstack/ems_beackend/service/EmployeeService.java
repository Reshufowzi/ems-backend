package com.teckstack.ems_beackend.service;

import com.teckstack.ems_beackend.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long id);

    List<EmployeeDto> getEmployees();

    EmployeeDto updateEmployee(Long id, EmployeeDto updateEmployee);

    void deleteEmployee(Long id);
}
