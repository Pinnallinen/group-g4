import React, { useState, useEffect } from 'react';
import axios from 'axios';
import "./WeatherDisplay.css";
import BarChart from '../visualizations/BarChart';

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
          <div>
            <p>Temperature: {weather.temperature}</p>
            <p>Windspeed: {weather.windSpeed}</p>
            <p>Cloud amount: {weather.cloudAmount}</p>
            <p>Rain amount: {weather.rainAmount}</p>
            <p>Rain intensity: {weather.rainIntensity}</p>
            <p>Visibility: {weather.visibility}</p>
            <p>Snow amount: {weather.snowAmount}</p>

            {/* Chart to visualize weather metrics */}
            <div className="chart-container">
                <BarChart data={[
                  weather.temperature, 
                  weather.windSpeed, 
                  weather.cloudAmount, 
                  weather.rainAmount, 
                  weather.rainIntensity, 
                  weather.snowAmount
                  ]} />
            </div>

          </div>
        ) : (
          <p>No weather data available.</p>
        )}
      </div>
    );
};

export default WeatherDisplay;