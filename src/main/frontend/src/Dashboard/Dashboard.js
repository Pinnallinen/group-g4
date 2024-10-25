import React, { useState } from 'react';
import SearchComponent from '../SearchComponent/SearchComponent';
import WeatherDisplay from '../WeatherDisplay/WeatherDisplay';
import "./Dashboard.css";

// Parent component for searching and displaying relevant data.

const Dashboard = () => {
    const [city, setCity] = useState('Helsinki');

    const handleSearch = (newCity) => {
        setCity(newCity);
    };

    return (
        <div>
            <h1>Weather Dashboard</h1>
            <SearchComponent onSearch={handleSearch}/>
            <WeatherDisplay city={city}/>
        </div>
    )
};

export default Dashboard;