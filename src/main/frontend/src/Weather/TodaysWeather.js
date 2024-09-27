import React, { useState, useEffect } from 'react';
import "./Weather.css";



const TodaysWeather = (props) => {
    const {weatherData} = props;

    return (
      <div className="container">
        <div className="weather">
            <h2>Today's Weather</h2>
            {weatherData ? (
          <div>
            <div className="temperature">{weatherData.temperature}°C</div>
            <div className="weather-info">{weatherData.weathercode}</div>
            <div className="temperature-range">Wind: {weatherData.windspeed} km/h</div>
          </div>
        ) : (
          <div>Loading weather data...</div>
        )}
        </div>
      </div>
    );
};

export default TodaysWeather;