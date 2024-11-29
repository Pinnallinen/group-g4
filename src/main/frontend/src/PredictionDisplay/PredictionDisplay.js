import React, { useContext } from 'react';
import { DataContext } from '../context/DataContext';
import "./PredictionDisplay.css";

const PredictionDisplay = () => {
  const { prediction, loading, error, city } = useContext(DataContext);
  if (loading) return <p>Loading data...</p>;
  if (error) return <p>{error}</p>;

  console.log(prediction);
  return (
    <div className="prediction-container">
    <h3>The current status and predictions for {city}</h3>
    {prediction ? (
      <div>
        <p class={prediction.actualStatus ? "delayed" : "not-delayed"}>The traffic currently, is <b>{prediction.actualStatus ? "delayed" : "not delayed"}</b>.</p><br></br>
        <p class={prediction.prediction ? "delayed" : "not-delayed"}>We predict that based on the weather the traffic will <b>{prediction.prediction ? "be delayed" : "not be delayed"}</b> soon.</p>
      </div>
    ) : (
      <p>No predictions available.</p>
    )}
  </div>
  );
};

export default PredictionDisplay;