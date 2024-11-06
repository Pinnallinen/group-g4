import React, { useState, useContext } from "react";
import { DataContext } from "../context/DataContext";
import "./SearchComponent.css";

const SearchComponent = () => {
  const { updateCity } = useContext(DataContext);
  const [query, setQuery] = useState('');

  const handleSearch = (e) => {
    e.preventDefault();
    if (query) {
      updateCity(query);
    }
  };

  return (
    <form onSubmit={handleSearch} className="search-form">
      <div className="search-bar">
        <input
          type="text"
          value={query}
          onChange={(e) => setQuery(e.target.value)}
          placeholder="Enter city"
        />
        <button type="submit">
          <i className="arrow-icon">→</i>
        </button>
      </div>
    </form>
  );
};

export default SearchComponent;