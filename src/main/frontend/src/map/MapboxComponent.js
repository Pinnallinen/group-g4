import React from 'react';
import ReactMapGL from 'react-map-gl';

const MapboxComponent = () => {
  const [viewport, setViewport] = React.useState({
    latitude: 37.7577,
    longitude: -122.4376,
    zoom: 8,
    width: '100%',
    height: '400px',
  });

  return (
    <ReactMapGL
      {...viewport}
      mapboxApiAccessToken="YOUR_MAPBOX_ACCESS_TOKEN"
      onViewportChange={(newViewport) => setViewport(newViewport)}
    />
  );
};

export default MapboxComponent;
