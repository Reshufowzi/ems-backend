package com.teckstack.ems_beackend.service;

import com.teckstack.ems_beackend.dto.EmployeeDto;
import com.teckstack.ems_beackend.entity.Employee;
import com.teckstack.ems_beackend.exception.ResourceNotFoundException;
import com.teckstack.ems_beackend.mapper.EmployerMapper;
import com.teckstack.ems_beackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployerMapper.mapToEmployee(employeeDto);

        return EmployerMapper.mapToEmployeeDTO(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
    Employee employeeById =  employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with the given id: " + id));
        return EmployerMapper.mapToEmployeeDTO(employeeById);
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployerMapper::mapToEmployeeDTO).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto updateEmployee) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exists with theb given id: " + id));

        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());
        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployerMapper.mapToEmployeeDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exists with theb given id: " + id));

        employeeRepository.deleteById(employee.getId());
    }
}
