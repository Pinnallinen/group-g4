import React, { useState, useEffect } from 'react';
import axios from 'axios';
import "./WeatherDisplay.css";

// Fetch and display weather data from the back-end.

const WeatherDisplay = ({city}) => {
    const [weather, setWeather] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
      const fetchWeatherData = async () => {
        if (city) {
          try {
            setLoading(true);
            const response = await axios.get(`/weather/${city}`);
            setWeather(response.data);
            setLoading(false);
          } catch (error) {
            setError('Failed to fetch weather data');
            setLoading(false);
          }
        }
      };
      fetchWeatherData();
    }, [city]);
    
    if (loading) return <p>Loading data...</p>;
    if (error) return <p>{error}</p>;

    return (
      <div>
        <h3>Current weather in {city}</h3>
        {weather ? (
          <p>Temperature: {weather.temperature}</p>
        ) : (
          <p>No weather data available.</p>
        )}
      </div>
    );
};

export default WeatherDisplay;