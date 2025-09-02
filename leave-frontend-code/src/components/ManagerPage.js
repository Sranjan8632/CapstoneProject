import React, { useEffect, useState } from "react";

function ManagerPage({ empId }) {
  const [pendingLeaves, setPendingLeaves] = useState([]);
  const [remarks, setRemarks] = useState({});

  useEffect(() => {
    fetch(`/leaves/viewleavehistory/${empId}`)
      .then((res) => res.json())
      .then((data) => {
        const appliedLeaves = data.filter((l) => l.leaveStatus === "APPLIED");
        setPendingLeaves(appliedLeaves);
      })
      .catch((err) => console.error("Error fetching pending leaves", err));
  }, [empId]);

  const handleDecision = async (leaveId, decision) => {
    if (decision === "REJECTED" && !remarks[leaveId]) {
      alert("Remarks are required when rejecting");
      return;
    }

    try {
      await fetch("/leaves/verifyleaverequest", {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          leaveId,
          managerId: empId,
          status: decision,
          remarks: decision === "REJECTED" ? remarks[leaveId] : ""
        })
      });
      alert("Leave " + decision);
      setPendingLeaves(pendingLeaves.filter((l) => l.id !== leaveId));
    } catch (err) {
      console.error("Error verifying leave", err);
      alert("Error verifying leave");
    }
  };

  const handleRemarkChange = (leaveId, value) => {
    setRemarks({ ...remarks, [leaveId]: value });
  };

  return (
    <div style={{ marginTop: "20px" }}>
      <h3>Manager Actions</h3>
      {pendingLeaves.length === 0 ? (
        <p>No pending leave requests.</p>
      ) : (
        <table border="1" cellPadding="5">
          <thead>
            <tr>
              <th>ID</th>
              <th>Employee</th>
              <th>From</th>
              <th>To</th>
              <th>Type</th>
              <th>Applied On</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {pendingLeaves.map((leave) => (
              <tr key={leave.id}>
                <td>{leave.id}</td>
                <td>{leave.empId}</td>
                <td>{leave.fromDate}</td>
                <td>{leave.toDate}</td>
                <td>{leave.leaveType}</td>
                <td>{leave.dateApplied}</td>
                <td>
                  <button onClick={() => handleDecision(leave.id, "APPROVED")}>Approve</button>
                  <br />
                  <input
                    type="text"
                    placeholder="Remarks (if reject)"
                    value={remarks[leave.id] || ""}
                    onChange={(e) => handleRemarkChange(leave.id, e.target.value)}
                  />
                  <button onClick={() => handleDecision(leave.id, "REJECTED")}>Reject</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default ManagerPage;
