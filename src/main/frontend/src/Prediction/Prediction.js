import React from 'react';

const Prediction = (props) => {
  const predictions = props.predictions;

  return (
    <div className="container">
      <div className="prediction-section">
        <h2>Predictions</h2>
        {predictions.length > 0 ? (
          <ul>
            {predictions.map((line) => (
              <li key={line.id}>
                <strong>{line.line}</strong>: {line.prediction}
              </li>
            ))}
          </ul>
        ) : (
          <p>Analyzing predictions...</p>
        )}
      </div>
    </div>
  );
};

export default Prediction;