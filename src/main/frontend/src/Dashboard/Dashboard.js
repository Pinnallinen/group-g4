import React from 'react';
import SearchComponent from '../SearchComponent/SearchComponent';
import WeatherDisplay from '../WeatherDisplay/WeatherDisplay';
import TransportDisplay from '../TransportDisplay/TransportDisplay';
import PredictionDisplay from '../PredictionDisplay/PredictionDisplay';
import FavoriteCities from '../FavoriteCities/FavoriteCities';
import "./Dashboard.css";


const Dashboard = () => {
    return (
        <div>
            <div className="top-bar">
                <div className="logo">Weather&Traffic</div>
                <div className="search-bar">
                    <SearchComponent />
                </div>
                <div className="tabs">
                    <a href="#home">Home</a>
                    <a href="#transport-status">Transport Status</a>
                    <a href="#contact">Map Feature</a>
                </div>
            </div>
            <div className="main-content">
                <div className="weather-data">
                    <WeatherDisplay />
                    <TransportDisplay />
                    <PredictionDisplay />
                </div>
                <div className="">
                    <FavoriteCities />
                </div>
            </div>
        </div>
    )
};

export default Dashboard;