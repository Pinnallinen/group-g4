import React, { useContext } from 'react';
import { DataContext } from '../context/DataContext';
import "./PredictionDisplay.css";

const PredictionDisplay = () => {
  const { prediction, loading, error, city } = useContext(DataContext);
  if (loading) return <p>Loading data...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div>
    <h3>Predictions for {city}</h3>
    {prediction ? (
      <div>
        <p>Predictions available.</p>
      </div>
    ) : (
      <p>No predictions available.</p>
    )}
  </div>
  );
};

export default PredictionDisplay;