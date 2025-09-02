package com.example.lms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.Employee;
import com.example.lms.entity.LeaveRequest;
import com.example.lms.database.EmployeeDatabase;
import com.example.lms.database.LeaveRequestDatabase;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    EmployeeDatabase employeeRepository;

    @Autowired
    LeaveRequestDatabase leaveRequestRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(String empid) {
        return employeeRepository.findById(empid);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<LeaveRequest> getEmployeeLeaveHistory(String empid) {
        return leaveRequestRepository.findByempid(empid);
    }

	@Override
	public Employee employeeValidation(Employee employee) {
		// TODO Auto-generated method stub
		Optional<Employee> opEmployee = employeeRepository.findByEmpidAndPassword(employee.getEmpid(), employee.getPassword());
		if(opEmployee.isEmpty()) {
			throw new RuntimeException("employee not found");
		}
		
		return opEmployee.get();
	}



}
