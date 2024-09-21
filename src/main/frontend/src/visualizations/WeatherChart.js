import React from 'react';
import { Line } from 'react-chartjs-2';

const WeatherChart = () => {
  const data = {
    labels: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'],
    datasets: [
      {
        label: 'Temperature (°C)',
        data: [22, 24, 19, 25, 27],
        fill: false,
        borderColor: 'rgb(75, 192, 192)',
        tension: 0.1,
      },
    ],
  };

  return <Line data={data} />;
};

export default WeatherChart;
