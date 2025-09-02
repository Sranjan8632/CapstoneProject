package com.example.lms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.LeaveRequest;
import com.example.lms.entity.LeaveStatus;
import com.example.lms.database.LeaveRequestDatabase;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestServiceImplementation implements LeaveRequestService {

    @Autowired
    private LeaveRequestDatabase leaveRequestRepository;


    @Override
    public LeaveRequest applyLeaveRequest(LeaveRequest leaveRequest) {
    	
        leaveRequest.setLeaveStatus(LeaveStatus.APPLIED);
        leaveRequest.setDateApplied(LocalDate.now());
        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public LeaveRequest verifyLeaveRequest(int leaveRequestId, boolean approve, String remarks) {
        Optional<LeaveRequest> leaveRequestOpt = leaveRequestRepository.findById(leaveRequestId);

        if (leaveRequestOpt.isPresent()) {
            LeaveRequest leaveRequest = leaveRequestOpt.get();
            if (approve) {
                leaveRequest.setLeaveStatus(LeaveStatus.APPROVED);
                leaveRequest.setRemarks(remarks);
            } else {
                leaveRequest.setLeaveStatus(LeaveStatus.REJECTED);
                leaveRequest.setRemarks(remarks); 
            }
            return leaveRequestRepository.save(leaveRequest);
        } else {
            throw new RuntimeException("Leave request not found with ID: " + leaveRequestId);
        }
    }

    @Override
    public LeaveRequest cancelLeaveRequest(int leaveRequestId) {
        Optional<LeaveRequest> leaveRequestOpt = leaveRequestRepository.findById(leaveRequestId);

        if (leaveRequestOpt.isPresent()) {
            LeaveRequest leaveRequest = leaveRequestOpt.get();
            if (leaveRequest.getLeaveStatus() == LeaveStatus.APPROVED) {
                throw new RuntimeException("Cannot cancel an approved leave request");
            }
            leaveRequest.setLeaveStatus(LeaveStatus.CANCELLED);
            return leaveRequestRepository.save(leaveRequest);
        } else {
            throw new RuntimeException("Leave request not found with ID: " + leaveRequestId);
        }
    }

    @Override
    public LeaveRequest withdrawLeaveRequest(int leaveRequestId) {
        Optional<LeaveRequest> leaveRequestOpt = leaveRequestRepository.findById(leaveRequestId);

        if (leaveRequestOpt.isPresent()) {
            LeaveRequest leaveRequest = leaveRequestOpt.get();
            if (leaveRequest.getLeaveStatus() == LeaveStatus.APPROVED) {
                throw new RuntimeException("Cannot withdraw an approved leave request");
            }
            leaveRequest.setLeaveStatus(LeaveStatus.WITHDRAWN);
            return leaveRequestRepository.save(leaveRequest);
        } else {
            throw new RuntimeException("Leave request not found with ID: " + leaveRequestId);
        }
    }

    @Override
    public Optional<LeaveRequest> getLeaveRequestById(int leaveRequestId) {
        return leaveRequestRepository.findById(leaveRequestId);
    }

    @Override
    public List<LeaveRequest> getLeaveHistoryByEmpid(String empid) {
        return leaveRequestRepository.findByempid(empid);
    }

    @Override
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

	@Override
	public List<LeaveRequest> getLeaveHistoryByManagerid(String managerid) {
		// TODO Auto-generated method stub
		return leaveRequestRepository.findByManagerid(managerid);
	}


}
