package com.example.lms.services;

import java.util.List;
import java.util.Optional;

import com.example.lms.entity.LeaveRequest;

public interface LeaveRequestService {

    LeaveRequest applyLeaveRequest(LeaveRequest leaveRequest);
    LeaveRequest verifyLeaveRequest(int leaveRequestId, boolean approve, String remarks);
    LeaveRequest cancelLeaveRequest(int leaveRequestId);
    LeaveRequest withdrawLeaveRequest(int leaveRequestId);
    Optional<LeaveRequest> getLeaveRequestById(int leaveRequestId);
    List<LeaveRequest> getLeaveHistoryByEmpid(String empid);
    List<LeaveRequest> getLeaveHistoryByManagerid(String managerid);
    List<LeaveRequest> getAllLeaveRequests();
}
