import React, { useState, useEffect } from 'react';
import axios from 'axios';
import "./PredictionDisplay.css";

const PredictionDisplay = ({city}) => {
  const [prediction, setPrediction] = useState(null);

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