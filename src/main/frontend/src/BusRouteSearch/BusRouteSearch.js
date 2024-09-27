import React from 'react';
import "./BusRouteSearch.css";

const BusRouteSearch = (props) => {
  const transportData = props.transportData;

  return (
    <div className="container">
      <div className="transit">
        <h2>Transit</h2>
        <div className="search-bar">
          <input type="text" placeholder="Search for a line or stop" />
        </div>
        <div className="next-bus">
        {transportData.length > 0 ? (
        <ul>
          {transportData.map((line) => (
            <li key={line.id}>
              {line.line} - Scheduled at {line.scheduledTime}
            </li>
          ))}
        </ul>
      ) : (
        <p>Loading transport data...</p>
      )}
        </div>
      </div>
    </div>
  );
};

export default BusRouteSearch;