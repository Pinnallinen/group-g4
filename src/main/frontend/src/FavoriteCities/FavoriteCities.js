import React, { useContext, useState } from 'react';
import { DataContext } from '../context/DataContext';
import './FavoriteCities.css';

const FavoriteCities = () => {
  const { favoriteCities, addFavoriteCity, updateCity, deleteFavoriteCity, error } = useContext(DataContext);
  const [newCity, setNewCity] = useState('');

  const handleAddCity = () => {
    if (newCity.trim() !== '') {
      const city = { cityName: newCity };
      addFavoriteCity(city);
      setNewCity('');
    }
  };

  const handleDeleteCity = (cityName) => {
    deleteFavoriteCity(cityName);
  };

  const handleLoadCity = (cityName) => {
    updateCity(cityName);
  };

  return (
    <div className="favorite-cities">
      <h3>Favorite Cities</h3>
      <div className="input-group">
        <input
          type="text"
          value={newCity}
          onChange={(e) => setNewCity(e.target.value)}
          placeholder="Add a new city"
        />
        <button className="add-button" onClick={handleAddCity}>Add</button>
      </div>
      {error && <p className="error">{error}</p>}
      <ul>
        {favoriteCities.map((city, index) => (
          <li key={index}>
            {city.cityName}
            <div className="action-buttons">
              <button onClick={() => handleLoadCity(city.cityName)}>Load</button>
              <button onClick={() => handleDeleteCity(city.cityName)}>Delete</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default FavoriteCities;