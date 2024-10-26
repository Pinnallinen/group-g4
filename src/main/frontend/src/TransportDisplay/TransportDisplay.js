import useFetchData from '../hooks/useFetchData';
import "./TransportDisplay.css";

const TransportDisplay = ({city}) => {

    const { data: transport, loading, error } = useFetchData(`/transport/${city}`);
    
    if (loading) return <p>Loading data...</p>;
    if (error) return <p>{error}</p>;

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