import './App.css';
import BarChart from "./visualizations/BarChart.js";
import DonutChart from "./visualizations/DonutChart.js";
import MapComponent from './map/MapComponent.js';
import WeatherChart from './visualizations/WeatherChart.js';


// TODO: mapbox / this component requires api key.
// import MapboxComponent from './map/MapboxComponent.js';
// Also requires API key
//import MapWithTrafficData from './map/MapWithTrafficData.js';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <BarChart />

        <DonutChart />

        <MapComponent />

        
      </header>
    </div>
  );
}

export default App;
