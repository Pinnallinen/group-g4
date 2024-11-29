import React from 'react';
import { Bar } from 'react-chartjs-2';
import { Chart as ChartJS, BarElement, CategoryScale, LinearScale, Tooltip, Legend } from 'chart.js';

ChartJS.register(BarElement, CategoryScale, LinearScale, Tooltip, Legend);

const VisibilityBar = ({ visibility }) => {
  visibility = visibility / 1000;
  const maxVisibility = 100;
  const visibilityPercentage = (visibility / maxVisibility) * 100;

  const data = {
    labels: ['Visibility (km)'],
    datasets: [
      {
        label: 'Visibility',
        data: [visibilityPercentage],
        // Display red for low, yellow for medium and green for high visibility
        backgroundColor: visibility < 4 ? 'rgba(230, 0, 0, 0.6)'
          : visibility < 10 ? 'rgba(255, 204, 0, 0.6)'
          : 'rgba(0, 128, 0, 0.6)',
        borderWidth: 1,
      },
    ],
  };

  const options = {
    indexAxis: 'y', // Horizontal bar
    maintainAspectRatio: false,
    scales: {
      x: {
        min: 0,
        max: 100,
        ticks: {
          callback: (value) => `${value}`,
        },
      },
    },
    plugins: {
      legend: { display: false },
      tooltip: {
        callbacks: {
          label: () => `Visibility: ${visibility} km`,
        },
      },
    },
  };

  return (
      <Bar data={data} options={options} />
  );
};

export default VisibilityBar;
