import './App.css';
import React, { useState, useEffect } from 'react';
import BarChart from "./visualizations/BarChart.js";
import DonutChart from "./visualizations/DonutChart.js";
import MapComponent from './map/MapComponent.js';
import WeatherChart from './visualizations/WeatherChart.js';

import BusRouteSearch from './BusRouteSearch/BusRouteSearch.js';
import TodaysWeather from './Weather/TodaysWeather.js';
import Prediction from './Prediction/Prediction.js';


// TODO: mapbox / this component requires api key.
// import MapboxComponent from './map/MapboxComponent.js';
// Also requires API key
//import MapWithTrafficData from './map/MapWithTrafficData.js';

function App() {
  const [weatherData, setWeatherData] = useState(null);
  const [transportData, setTransportData] = useState([]);
  const [predictions, setPredictions] = useState([]);
    
  // Fetch weather data using Open-Meteo's API
  const fetchWeatherData = async () => {
      fetch(
      'https://api.open-meteo.com/v1/forecast?latitude=61.497&longitude=-23.753&current_weather=true'
      )
      .then((response) => response.json())
      .then((data) => {
          setWeatherData(data.current_weather);
      })
      .catch((error) => {
          console.error('Error fetching weather data:', error);
      });
  };

  const fetchTransportData = async () => {
    // Replace with real public transport API URL
    const transportMockData = [
      { id: 1, line: '5R Fulton Rapid', scheduledTime: '3:12PM' },
      { id: 2, line: '5 Fulton', scheduledTime: '3:20PM' },
      { id: 3, line: '21 Hayes', scheduledTime: '3:30PM' },
    ];
    return new Promise((resolve) => setTimeout(() => resolve(transportMockData), 1000));
  };

  useEffect(() => {
    const fetchData = async () => {
      await fetchWeatherData();

      const transport = await fetchTransportData();
      setTransportData(transport);
    };
    fetchData();
  }, []);

  // Simple prediction function based on weather conditions
  useEffect(() => {
    if (weatherData && transportData.length > 0) {
      const newPredictions = transportData.map((line) => {
        let prediction = 'On Time';
        // TODO change these to real weather codes
        if (weatherData.weather === 'rainy' || weatherData.weather === 'snowy') {
          prediction = 'Delayed';
        } else if (weatherData.windspeed > 10) {
          prediction = 'Delayed';
        }
        return { ...line, prediction };
      });
      setPredictions(newPredictions);
    }
  }, [weatherData, transportData]);

  return (
    <div className="App">

      <body>
        <MapComponent />

        <BusRouteSearch transportData={transportData}/>

        <TodaysWeather weatherData={weatherData}/>

        <Prediction predictions={predictions}/>
      </body>
    </div>
  );
}

export default App;
