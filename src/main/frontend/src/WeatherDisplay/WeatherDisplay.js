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
    <div className="weather-container">
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
        <div>
          
          <div className="info-box">
            <p>Temperature: {Math.round(weather.temperature)} °C</p>
            <p>Windspeed: {Math.round(weather.windSpeed)} m/s</p>
            <p>Rain amount: {weather.rainAmount} mm</p>
            <p>Rain intensity: {weather.rainIntensity} mm/h</p>
            <p>Visibility: {weather.visibility / 1000} km</p>
            <p>Snow amount: {weather.snowAmount} cm</p>
          </div>

          {/* Chart to visualize weather metrics */}
          {selectedElements.metrics && (
          <div className="info-box">
            <div className="chart-container">
              <BarChart data={[
                weather.temperature,
                weather.windSpeed,
                weather.rainAmount,
                weather.rainIntensity,
                weather.snowAmount
              ]} />
              </div>
          </div>)}
          {selectedElements.clouds && (
          <div className="info-box">
            <div className="chart-container">
              <DonutChart data={[
                weather.cloudAmount,
                8 - weather.cloudAmount
              ]}/>
            </div>
          </div>)}
          {selectedElements.visibility && (
          <div className="info-box">
            <div className="chart-container">
              <VisibilityBar visibility={weather.visibility} />
            </div>
          </div>)}
        </div>
      ) : (
        <p>No weather data available.</p>
      )}
    </div>
  );
};

export default WeatherDisplay;
