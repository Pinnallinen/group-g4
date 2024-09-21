import React from 'react';
import DeckGL from '@deck.gl/react';
import { StaticMap } from 'react-map-gl';
import { HeatmapLayer } from '@deck.gl/aggregation-layers';

const MapWithTrafficData = () => {
  const INITIAL_VIEW_STATE = {
    latitude: 37.7749,
    longitude: -122.4194,
    zoom: 12,
    pitch: 0,
    bearing: 0,
  };

  const heatmapLayer = new HeatmapLayer({
    data: [{ position: [-122.42177834, 37.78346622], weight: 1 }],
    getPosition: d => d.position,
    getWeight: d => d.weight,
  });

  return (
    <DeckGL
      initialViewState={INITIAL_VIEW_STATE}
      controller={true}
      layers={[heatmapLayer]}
    >
      <StaticMap mapboxApiAccessToken="YOUR_MAPBOX_ACCESS_TOKEN" />
    </DeckGL>
  );
};

export default MapWithTrafficData;
