import React, { useState, useEffect } from 'react';
import axios from 'axios';
import "./TransportDisplay.css";

const TransportDisplay = ({city}) => {
    const [transport, setTransport] = useState(null);
    
    return (
        <div>
          <h3>Transport in {city}</h3>
          {transport ? (
            <div>
              <p>Transport data available.</p>
            </div>
          ) : (
            <p>No transport data available.</p>
          )}
        </div>
      );
};

export default TransportDisplay;