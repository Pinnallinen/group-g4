import React, { createContext, useState, useEffect } from 'react';
import axios from 'axios';

export const DataContext = createContext();

export const DataProvider = ({ children }) => {
  const [city, setCity] = useState('Helsinki');
  const [weather, setWeather] = useState(null);
  const [transport, setTransport] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      setError(null);

      try {
        const [weatherResponse, transportResponse] = await Promise.all([
          axios.get(`/weather/${city}`),
          axios.get(`/transport/status/${city}`)
        ]);

        setWeather(weatherResponse.data);
        setTransport(transportResponse.data);
      } catch (err) {
        setError('Failed to fetch data');
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [city]);

  const updateCity = (newCity) => {
    console.log('City updated to:', newCity);
    setCity(newCity);
  };

  return (
    <DataContext.Provider value={{ city, weather, transport, loading, error, updateCity }}>
      {children}
    </DataContext.Provider>
  );
};
