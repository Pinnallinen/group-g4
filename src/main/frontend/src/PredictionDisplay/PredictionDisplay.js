import useFetchData from '../hooks/useFetchData';
import "./PredictionDisplay.css";

const PredictionDisplay = ({city}) => {
  const { data: prediction, loading, error } = useFetchData(`/predictions/${city}`);

  if (loading) return <p>Loading data...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div>
    <h3>Predictions for {city}</h3>
    {prediction ? (
      <div>
        <p>Predictions available.</p>
      </div>
    ) : (
      <p>No predictions available.</p>
    )}
  </div>
  );
};

export default PredictionDisplay;