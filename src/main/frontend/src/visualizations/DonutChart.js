import React from 'react';
import { Doughnut } from 'react-chartjs-2';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';

// Register Chart.js components (required for v3+)
ChartJS.register(ArcElement, Tooltip, Legend);

const DonutChart = ({data}) => {
  // Data for the donut chart
  const chartData = {
    labels: ['Cloudiness', 'Clear'],
    datasets: [
      {
        data: data,
        backgroundColor: [
          'rgba(66, 106, 173, 0.7)',
          'rgba(221, 227, 237, 0.7)',
        ],
        borderWidth: 1,
      },
    ],
  };

  // Optional: Chart configuration options
  const options = {
    cutout: '70%', // This makes it a donut chart (controls the size of the center hole)
    responsive: true,
    maintainAspectRatio: false,
    circumference: 180,
    rotation: -90,
    plugins: {
      legend: {
        position: 'top',
      },
      tooltip: {
        enabled: true,
      },
    },
  };

  return <Doughnut data={chartData} options={options} />;
};

export default DonutChart;
