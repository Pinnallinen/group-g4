import React, { useState } from 'react';
import SearchComponent from '../SearchComponent/SearchComponent';
import WeatherDisplay from '../WeatherDisplay/WeatherDisplay';
import TransportDisplay from '../TransportDisplay/TransportDisplay';
import PredictionDisplay from '../PredictionDisplay/PredictionDisplay';
import "./Dashboard.css";

// Parent component for searching and displaying relevant data.

const Dashboard = () => {
    const [city, setCity] = useState('Helsinki');

    const handleSearch = (newCity) => {
        setCity(newCity);
    };

    return (
        <div>
            <h1>Weather and Traffic</h1>
            <SearchComponent onSearch={handleSearch}/>
            <WeatherDisplay city={city}/>
            <TransportDisplay city={city}/>
            <PredictionDisplay city={city}/>
        </div>
    )
};

export default Dashboard;