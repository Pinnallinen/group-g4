import React from 'react';
import SearchComponent from '../SearchComponent/SearchComponent';
import WeatherDisplay from '../WeatherDisplay/WeatherDisplay';
import TransportDisplay from '../TransportDisplay/TransportDisplay';
import PredictionDisplay from '../PredictionDisplay/PredictionDisplay';
import "./Dashboard.css";

// Parent component for searching and displaying relevant data.

const Dashboard = () => {
    return (
        <div>
            <h1>Weather and Traffic</h1>
            <SearchComponent/>
            <WeatherDisplay/>
            <TransportDisplay/>
            <PredictionDisplay/>
        </div>
    )
};

export default Dashboard;