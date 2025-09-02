import React, { useState } from "react";
import EmployeePage from "./components/EmployeePage";
import ManagerPage from "./components/ManagerPage";
import HolidayList from "./components/HolidayList";
import LeaveHistory from "./components/LeaveHistory";

function App() {
  const [role, setRole] = useState("");
  const [empId, setEmpId] = useState("");
  const [password, setPassword] = useState("");
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [loggedInUser, setLoggedInUser] = useState(null);

  const handleLogin = async () => {
    if (!empId || !password || !role) {
      alert("Please fill all fields");
      return;
    }

    try {
      const res = await fetch(`/employees/getemployeebyid/${empId}`);
      if (!res.ok) {
        alert("Employee not found");
        return;
      }

      const data = await res.json();
      if (data.password === password) {
        setIsLoggedIn(true);
        setLoggedInUser(data);
        alert("Login successful");
      } else {
        alert("Invalid password");
      }
    } catch (err) {
      console.error("Error during login", err);
      alert("Error connecting to backend");
    }
  };

  const handleLogout = () => {
    setRole("");
    setEmpId("");
    setPassword("");
    setIsLoggedIn(false);
    setLoggedInUser(null);
  };

  return (
    <div style={{ margin: "20px" }}>
      <h2>Leave Management System</h2>

      {!isLoggedIn ? (
        <div>
          <p>Login:</p>
          <select value={role} onChange={(e) => setRole(e.target.value)}>
            <option value="">Select Role</option>
            <option value="employee">Employee</option>
            <option value="manager">Manager</option>
          </select>
          <br />
          <input
            type="text"
            placeholder="Enter Employee ID"
            value={empId}
            onChange={(e) => setEmpId(e.target.value)}
          />
          <br />
          <input
            type="password"
            placeholder="Enter Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <br />
          <button onClick={handleLogin}>Login</button>
        </div>
      ) : (
        <div>
          <p>
            Logged in as <b>{loggedInUser.firstName} {loggedInUser.lastName}</b>  
            (Role: {role}, ID: {loggedInUser.empId})
          </p>
          <button onClick={handleLogout}>Logout</button>

          {role === "employee" && <EmployeePage empId={loggedInUser.empId} />}
          {role === "manager" && <ManagerPage empId={loggedInUser.empId} />}

          <HolidayList />
          <LeaveHistory empId={loggedInUser.empId} />
        </div>
      )}
    </div>
  );
}

export default App;
