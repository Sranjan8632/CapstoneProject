package com.example.lms.database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.Employee;

@Repository
public interface EmployeeDatabase extends JpaRepository<Employee, String> {
	Optional<Employee> findByEmpidAndPassword(String empid,String password);

}
