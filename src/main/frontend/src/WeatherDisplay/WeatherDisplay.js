import React, { useContext, useState } from "react";
import "./WeatherDisplay.css";
import { DataContext } from "../context/DataContext";
import BarChart from '../visualizations/BarChart';
import DonutChart from '../visualizations/DonutChart';

import VisibilityBar from '../visualizations/VisibilityBar';

// Fetch and display weather data from the back-end.

const WeatherDisplay = () => {
  const { weather, loading, error, city } = useContext(DataContext);
  const [selectedElements, setSelectedElements] = useState({
    metrics: false,
    clouds: false,
    visibility: false
  });

  const handleCheckboxChange = (element) => {
    setSelectedElements((prev) => ({
      ...prev,
      [element]: !prev[element],
    }));
  };

  if (loading) return <p>Loading data...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div>
      <h3>Current weather in {city}</h3>
      <div className="checkbox-container">
        <p>Display weather graphics: </p>
        <label>
          <input
            type="checkbox"
            checked={selectedElements.temperature}
            onChange={() => handleCheckboxChange('metrics')}
          />
          Weather Metrics
        </label>
        <label>
          <input
            type="checkbox"
            checked={selectedElements.windSpeed}
            onChange={() => handleCheckboxChange('clouds')}
          />
          Cloudiness
        </label>
        <label>
          <input
            type="checkbox"
            checked={selectedElements.cloudiness}
            onChange={() => handleCheckboxChange('visibility')}
          />
          Visibility
        </label>
      </div>

      {weather ? (
        <div className="weather-container">
          
          <div>
            <p>Temperature: {weather.temperature}</p>
            <p>Windspeed: {weather.windSpeed}</p>
            <p>Rain amount: {weather.rainAmount}</p>
            <p>Rain intensity: {weather.rainIntensity}</p>
            <p>Visibility: {weather.visibility}</p>
            <p>Snow amount: {weather.snowAmount}</p>
          </div>

          {/* Chart to visualize weather metrics */}
          {selectedElements.metrics && (
          <div className="chart-container">
            <BarChart data={[
              weather.temperature, 
              weather.windSpeed,  
              weather.rainAmount, 
              weather.rainIntensity, 
              weather.snowAmount
            ]} />
            </div>)}
          {selectedElements.clouds && (
          <div className="chart-container">
            <DonutChart data={[
              weather.cloudAmount,
              8 - weather.cloudAmount
            ]}/>
          </div>)}
          {selectedElements.visibility && (
          <div className="chart-container">
            <VisibilityBar visibility={weather.visibility} />
          </div>)}
        </div>
      ) : (
        <p>No weather data available.</p>
      )}
    </div>
  );
};

export default WeatherDisplay;
