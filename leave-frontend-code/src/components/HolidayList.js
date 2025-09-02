import React, { useEffect, useState } from "react";

function HolidayList() {
  const [holidays, setHolidays] = useState([]);

  useEffect(() => {
    fetch("/holidays/viewholidaylist")
      .then((res) => res.json())
      .then((data) => setHolidays(data))
      .catch((err) => console.error("Error fetching holidays", err));
  }, []);

  return (
    <div style={{ marginTop: "20px" }}>
      <h3>Public Holidays</h3>
      {holidays.length === 0 ? (
        <p>No holidays found.</p>
      ) : (
        <table border="1" cellPadding="5">
          <thead>
            <tr>
              <th>Date</th>
              <th>Holiday</th>
            </tr>
          </thead>
          <tbody>
            {holidays.map((h) => (
              <tr key={h.id}>
                <td>{h.date}</td>
                <td>{h.holidayDetails}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default HolidayList;
