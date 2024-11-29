import React from 'react';
import SearchComponent from '../SearchComponent/SearchComponent';
import WeatherDisplay from '../WeatherDisplay/WeatherDisplay';
import TransportDisplay from '../TransportDisplay/TransportDisplay';
import PredictionDisplay from '../PredictionDisplay/PredictionDisplay';
import FavoriteCities from '../FavoriteCities/FavoriteCities';
import "./Dashboard.css";


const Dashboard = () => {
    return (
        <div className="dashboard">
            <div className="top-bar">
                <div className="logo">Weather&Traffic</div>
                <SearchComponent className="search-component" />
            </div>
            <div className="main-content">
                <WeatherDisplay />
                <TransportDisplay />
                <PredictionDisplay />
                <FavoriteCities />
            </div>
        </div>
    )
};

export default Dashboard;