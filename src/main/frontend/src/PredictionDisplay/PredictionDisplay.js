import React, { useContext } from 'react';
import { DataContext } from '../context/DataContext';
import "./PredictionDisplay.css";

const PredictionDisplay = () => {
  const { prediction, loading, error, city } = useContext(DataContext);
  if (loading) return <p>Loading data...</p>;
  if (error) return <p>{error}</p>;

  console.log(prediction);
  return (
    <div>
    <h3>Predictions for {city}</h3>
    {prediction ? (
      <div>
        <p>The traffic currently is {prediction.actualStatus ? "delayed" : "not delayed"}.</p>
        <p>We predict that based on the weather the traffic will {prediction.prediction ? "be delayed" : "not be delayed"} soon.</p>
      </div>
    ) : (
      <p>No predictions available.</p>
    )}
  </div>
  );
};

export default PredictionDisplay;