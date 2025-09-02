package com.example.lms.services;

import java.util.List;
import java.util.Optional;

import com.example.lms.entity.Employee;
import com.example.lms.entity.LeaveRequest;

public interface EmployeeService {

	Employee addEmployee(Employee employee);
	Optional<Employee> getEmployeeById(String empid);
	List<Employee> getAllEmployees();
    List<LeaveRequest> getEmployeeLeaveHistory(String empid);
    Employee employeeValidation(Employee employee);

}
