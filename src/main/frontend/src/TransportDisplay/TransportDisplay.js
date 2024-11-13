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
        {transport && transport.trafficStatus && transport.trafficStatus != "" ? (
          <div>
            <p>The traffic in the city goes <b>{Math.round(transport.freeFlowPercentage)}%</b> of its free flow speed.</p>
            <p class={transport.freeFlowPercentage < 75 ? 'slow' : (transport.freeFlowPercentage < 90 ? 'platooning' : 'fast')}>The traffic in the city is <b>{transport.trafficStatus}</b>.</p>
          </div>
        ) : (
          <p>No transport data available.</p>
        )}
      </div>
    );
};

export default TransportDisplay;