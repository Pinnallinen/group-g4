import React, { useContext } from 'react';
import { DataContext } from '../context/DataContext';
import "./TransportDisplay.css";

const TransportDisplay = () => {
  
  const { transport, loading, error, city } = useContext(DataContext);

  if (loading) return <p>Loading data...</p>;
  if (error) return <p>{error}</p>;

  return (
      <div>
        <h3>Transport in {city}</h3>
        {transport ? (
          <div>
            <p>Transport data available.</p>
          </div>
        ) : (
          <p>No transport data available.</p>
        )}
      </div>
    );
};

export default TransportDisplay;