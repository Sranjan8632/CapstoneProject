import React, { useState, useEffect } from "react";

function EmployeePage({ empId }) {
  const [fromDate, setFromDate] = useState("");
  const [toDate, setToDate] = useState("");
  const [leaveType, setLeaveType] = useState("CASUAL");
  const [myLeaves, setMyLeaves] = useState([]);

  const fetchLeaves = () => {
    fetch(`/leaves/viewleavehistory/${empId}`)
      .then((res) => res.json())
      .then((data) => setMyLeaves(data))
      .catch((err) => console.error("Error fetching leaves", err));
  };

  useEffect(() => {
    fetchLeaves();
  }, [empId]);

  const applyLeave = async () => {
    try {
      let res = await fetch("/leaves/applyleaverequest", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ empId, fromDate, toDate, leaveType })
      });
      let data = await res.json();
      alert("Leave applied: " + JSON.stringify(data));
      fetchLeaves();
    } catch (err) {
      console.error("Error applying leave", err);
      alert("Error applying leave");
    }
  };

  const withdrawLeave = async (leaveId) => {
    try {
      await fetch(`/leaves/withdrawleave/${leaveId}`, { method: "PUT" });
      alert("Leave withdrawn");
      fetchLeaves();
    } catch (err) {
      console.error("Error withdrawing leave", err);
      alert("Error withdrawing leave");
    }
  };

  const cancelLeave = async (leaveId) => {
    try {
      await fetch(`/leaves/cancelleave/${leaveId}`, { method: "PUT" });
      alert("Leave cancelled");
      fetchLeaves();
    } catch (err) {
      console.error("Error cancelling leave", err);
      alert("Error cancelling leave");
    }
  };

  return (
    <div style={{ marginTop: "20px" }}>
      <h3>Employee Actions</h3>
      <div>
        <label>From: </label>
        <input type="date" value={fromDate} onChange={(e) => setFromDate(e.target.value)} />
        <br />
        <label>To: </label>
        <input type="date" value={toDate} onChange={(e) => setToDate(e.target.value)} />
        <br />
        <label>Type: </label>
        <select value={leaveType} onChange={(e) => setLeaveType(e.target.value)}>
          <option value="CASUAL">CASUAL</option>
          <option value="MEDICAL">MEDICAL</option>
        </select>
        <br />
        <button onClick={applyLeave}>Apply Leave</button>
      </div>

      <h3 style={{ marginTop: "20px" }}>My Leave Requests</h3>
      {myLeaves.length === 0 ? (
        <p>No leave records.</p>
      ) : (
        <table border="1" cellPadding="5">
          <thead>
            <tr>
              <th>ID</th>
              <th>From</th>
              <th>To</th>
              <th>Type</th>
              <th>Status</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {myLeaves.map((leave) => (
              <tr key={leave.id}>
                <td>{leave.id}</td>
                <td>{leave.fromDate}</td>
                <td>{leave.toDate}</td>
                <td>{leave.leaveType}</td>
                <td>{leave.leaveStatus}</td>
                <td>
                  {leave.leaveStatus === "APPLIED" && (
                    <button onClick={() => withdrawLeave(leave.id)}>Withdraw</button>
                  )}
                  {leave.leaveStatus === "APPROVED" && (
                    <button onClick={() => cancelLeave(leave.id)}>Cancel</button>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default EmployeePage;
