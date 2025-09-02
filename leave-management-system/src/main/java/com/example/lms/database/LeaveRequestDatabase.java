package com.example.lms.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.LeaveRequest;

@Repository
public interface LeaveRequestDatabase extends JpaRepository<LeaveRequest, Integer> {
    List<LeaveRequest> findByempid(String empid);
    List<LeaveRequest> findByManagerid(String managerid);

}
