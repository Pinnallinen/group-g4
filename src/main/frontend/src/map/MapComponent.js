import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';

const MapComponent = () => {
  return (
    <MapContainer center={[61.497, 23.753]} zoom={13} style={{ height: '600px', width: '90%' }}>
      <TileLayer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        attribution="&copy; OpenStreetMap contributors"
      />
      <Marker position={[61.497, 23.753]}>
        <Popup>Hello world!</Popup>
      </Marker>
    </MapContainer>
  );
};

export default MapComponent;
