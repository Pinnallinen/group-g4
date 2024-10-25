import React, { useState } from 'react';
import "./SearchComponent.css";

const SearchComponent = ({city, onSearch}) => {
  
  const [query, setQuery] = useState(city);

  const handleSearch = (e) => {
    e.preventDefault();
    if (query) {
      onSearch(query);
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