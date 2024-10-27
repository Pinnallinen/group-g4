import React, { useState, useContext } from 'react';
import { DataContext } from '../context/DataContext';
import "./SearchComponent.css";

const SearchComponent = () => {
  
  const { city, updateCity } = useContext(DataContext);
  const [query, setQuery] = useState(city);

  const handleSearch = (e) => {
    e.preventDefault();
    if (query) {
      updateCity(query);
    }
  };

  return (
    <form onSubmit={handleSearch}>
      <input
        type="text"
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        placeholder="Enter city"
      />
      <button type="submit">Search</button>
    </form>
  );
};

export default SearchComponent;