import React, { useState } from 'react';
import SearchComponent from '../SearchComponent/SearchComponent';
import WeatherDisplay from '../WeatherDisplay/WeatherDisplay';
import "./Dashboard.css";

// Parent component for searching and displaying relevant data.

const Dashboard = () => {
    const [query, setQuery] = useState('Helsinki');

    const handleSearch = (newQuery) => {
        setQuery(newQuery);
    };

    return (
        <div>
            <h1>Weather Dashboard</h1>
            <SearchComponent onSearch={handleSearch}/>
            <WeatherDisplay query={query}/>
        </div>
    )
};

export default Dashboard;