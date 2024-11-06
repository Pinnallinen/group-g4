import React, { createContext, useState, useEffect } from 'react';
import axios from 'axios';

export const DataContext = createContext();

export const DataProvider = ({ children }) => {
  const [city, setCity] = useState('Helsinki');
  const [weather, setWeather] = useState(null);
  const [transport, setTransport] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [favoriteCities, setFavoriteCities] = useState([]);

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

    if (city) {
      fetchData();
    }
  }, [city]);

  useEffect(() => {
    const fetchFavoriteCities = async () => {
      try {
        const response = await axios.get('/weather/favorites');
        const favoriteCities = response.data;
        setFavoriteCities(favoriteCities);

        // Set the initial city to Helsinki if the favorite list is empty
        if (favoriteCities.length > 0) {
          setCity(favoriteCities[0].cityName);
        } else {
          setCity('Helsinki');
        }
      } catch (err) {
        // Remove the error message and set the initial city to Helsinki
        setFavoriteCities([]);
        setCity('Helsinki');
      }
    };

    fetchFavoriteCities();
  }, []);

  const updateCity = (newCity) => {
    console.log('City updated to:', newCity);
    setCity(newCity);
  };

  const addFavoriteCity = async (city) => {
    // Check if the city is already in the favorite list
    const cityExists = favoriteCities.some(favCity => favCity.cityName === city.cityName);
    if (cityExists) {
      setError(`City ${city.cityName} is already in the favorite list`);
      return;
    }

    try {
      const response = await axios.post('/weather/favorites', city);
      if (response.status === 200) {
        setFavoriteCities([...favoriteCities, city]);
      } else {
        throw new Error('Failed to add city to favorites');
      }
    } catch (err) {
      setError('Failed to add city to favorites');
    }
  };

  const deleteFavoriteCity = async (cityName) => {
    try {
      const response = await axios.delete(`/weather/favorites/${cityName}`);
      if (response.status === 200) {
        setFavoriteCities(favoriteCities.filter(city => city.cityName !== cityName));
      } else {
        throw new Error('Failed to delete city from favorites');
      }
    } catch (err) {
      setError('Failed to delete city from favorites');
    }
  };

  return (
    <DataContext.Provider value={{ city, weather, transport, loading, error, favoriteCities, updateCity, addFavoriteCity, deleteFavoriteCity }}>
      {children}
    </DataContext.Provider>
  );
};