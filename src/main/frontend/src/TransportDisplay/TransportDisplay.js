import React, { useContext } from 'react';
import { DataContext } from '../context/DataContext';
import GaugeComponent from 'react-gauge-component';
import "./TransportDisplay.css";

const TransportDisplay = () => {
  
  const { transport, loading, error, city } = useContext(DataContext);

  if (loading) return <p>Loading data...</p>;
  if (error) return <p>{error}</p>;

  return (
      <div className="transport-container">
        <h3>Transport in {city}</h3>
        {transport && transport.trafficStatus && transport.trafficStatus != "" ? (
          <div>
            <div class={'cauge-container'}>
              <GaugeComponent 
                arc={{
                  subArcs: [
                    {
                      limit: 75,
                      color: '#f02020',
                      showTick: true
                    },
                    {
                      limit: 90,
                      color: '#f0f020',
                      showTick: true
                    },
                    {
                      limit: 100,
                      color: '#20f020',
                      showTick: true
                    },
                  ]
                }}
                value={Math.round(transport.freeFlowPercentage)}
              />
            </div>
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