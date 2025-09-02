package com.example.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.lms.entity.Employee;
import com.example.lms.entity.LeaveRequest;
import com.example.lms.services.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
    private EmployeeService employeeService;

    @PostMapping("/addemployee")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/getemployeebyid/{empid}")
    public Optional<Employee> getEmployeeById(@PathVariable String empid) {
        return employeeService.getEmployeeById(empid);
    }

    @GetMapping("/getallemployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/getleavehistorybyempid/{empid}")
    public List<LeaveRequest> getEmployeeLeaveHistory(@PathVariable String empid) {
        return employeeService.getEmployeeLeaveHistory(empid);
    }
    @PostMapping
    public ResponseEntity<Employee> loginValidate(@RequestBody Employee employee){
    	Employee e = employeeService.employeeValidation(employee);
    	return new ResponseEntity<Employee>(e,HttpStatus.OK);
    }
    @GetMapping("/logout")
	public String logout() {
	
		return "Session invalidated";
	}

}
