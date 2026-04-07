package com.teckstack.ems_beackend.repository;

import com.teckstack.ems_beackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
