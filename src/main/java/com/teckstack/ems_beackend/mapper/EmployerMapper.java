package com.teckstack.ems_beackend.mapper;

import com.teckstack.ems_beackend.dto.EmployeeDto;
import com.teckstack.ems_beackend.entity.Employee;

public class EmployerMapper {
    public static EmployeeDto mapToEmployeeDTO(Employee employee){
        return new EmployeeDto(employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail());
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail());
    }
}
