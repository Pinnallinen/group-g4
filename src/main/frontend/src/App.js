import './App.css';
import React from 'react';
import { DataProvider } from './context/DataContext.js';
import Dashboard from './Dashboard/Dashboard.js';

function App() {

  return (
    <div className="App">
      <DataProvider>
        <Dashboard/>
      </DataProvider>
    </div>
  );
}

export default App;
