import React, { useEffect, useState } from "react";

function LeaveHistory({ empId }) {
  const [history, setHistory] = useState([]);

  useEffect(() => {
    if (empId) {
      fetch(`/leaves/viewleavehistory/${empId}`)
        .then((res) => res.json())
        .then((data) => setHistory(data))
        .catch((err) => console.error("Error fetching history", err));
    }
  }, [empId]);

  return (
    <div style={{ marginTop: "20px" }}>
      <h3>Leave History</h3>
      {history.length === 0 ? (
        <p>No leave records found.</p>
      ) : (
        <table border="1" cellPadding="5">
          <thead>
            <tr>
              <th>ID</th>
              <th>From</th>
              <th>To</th>
              <th>Type</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {history.map((l) => (
              <tr key={l.id}>
                <td>{l.id}</td>
                <td>{l.fromDate}</td>
                <td>{l.toDate}</td>
                <td>{l.leaveType}</td>
                <td>{l.leaveStatus}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default LeaveHistory;
