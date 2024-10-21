import React, { useState, useEffect } from 'react';
import axios from 'axios';
import "./WeatherDisplay.css";

// Fetch and display weather data from the back-end.

const WeatherDisplay = ({query}) => {
    const [weather, setWeather] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
      const fetchWeatherData = async () => {
        if (query) {
          try {
            setLoading(true);
            const response = await axios.get(`/weather/${query}`);
            setWeather(response.data);
            setLoading(false);
          } catch (error) {
            setError('Failed to fetch weather data');
            setLoading(false);
          }
        }
      };
      fetchWeatherData();
    }, [query]);
    
    if (loading) return <p>Loading data...</p>;
    if (error) return <p>{error}</p>;

    return (
      <div>
        <h3>Current weather in {query}</h3>
        {weather ? (
          <p>Temperature: {weather.temperature}</p>
        ) : (
          <p>No weather data available.</p>
        )}
      </div>
    );
};

export default WeatherDisplay;