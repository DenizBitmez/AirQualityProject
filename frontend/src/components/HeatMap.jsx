import React, { useEffect, useState, useCallback } from 'react';
import ReactMapGL, {Source, Layer, Popup} from 'react-map-gl';
import axios from 'axios';
import 'mapbox-gl/dist/mapbox-gl.css';

const HeatMap = ({ setAnomalies, fetchPollutionData, setLatitude, setLongitude }) => {
    const [viewport, setViewport] = useState({
        latitude: 38.925533,
        longitude: 33.866287,
        zoom: 5,
        bounds: null
    });
    const [heatmapData, setHeatmapData] = useState({
        type: 'FeatureCollection',
        features: []
    });
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const [popupInfo, setPopupInfo] = useState(null);

    const calculateMapBounds = useCallback((map) => {
        const bounds = map.getBounds();
        return {
            minLat: bounds.getSouth(),
            maxLat: bounds.getNorth(),
            minLon: bounds.getWest(),
            maxLon: bounds.getEast()
        };
    }, []);

    const calculateGridSize = (zoomLevel) => {
        switch (true) {
            case zoomLevel <= 5:
                return 1.0;   // ~100km
            case zoomLevel <= 8:
                return 0.1;   // ~10km
            case zoomLevel <= 12:
                return 0.01;  // ~1km
            default:
                return 0.001; // ~100m
        }
    };

    useEffect(() => {
        if (!viewport.bounds) return;

        const fetchData = async () => {
            setLoading(true);
            setError(null);
            try {
                const { minLat, maxLat, minLon, maxLon } = viewport.bounds;
                const gridSize = calculateGridSize(Math.floor(viewport.zoom));
                const zoomLevel = Math.floor(viewport.zoom);

                const url = `${process.env.REACT_APP_API_BASE_URL}/api/quality/heatmap?` +
                    `minLat=${minLat}&maxLat=${maxLat}&` +
                    `minLon=${minLon}&maxLon=${maxLon}&` +
                    `gridSize=${gridSize}&zoomLevel=${zoomLevel}`;

                const response = await fetch(url);

                if (!response.ok) {
                    const errorData = await response.json().catch(() => null);
                    throw new Error(errorData?.message || `HTTP error! status: ${response.status}`);
                }

                const data = await response.json();

                if (!data || !Array.isArray(data.features)) {
                    throw new Error('Geçersiz veri formatı');
                }

                setHeatmapData(data);
            } catch (err) {
                setError(`Hata: ${err.message}`);
                setLoading(false);
            }
        };

        const debounceTimer = setTimeout(fetchData, 500);
        return () => clearTimeout(debounceTimer);
    }, [viewport.bounds, viewport.zoom]);

    const fetchAnomalies = async (lat, lon) => {
        try {
            const response = await axios.get('http://localhost:8080/api/quality/regional-anomalies', {
                params: {
                    lat,
                    lon,
                    radiusKm: 25,
                },
            });

            if (response.data) {
                setAnomalies(response.data);  // setAnomalies fonksiyonunu burada kullanıyoruz
            }
        } catch (error) {
            console.error('Anomali verisi alınırken hata:', error);
        }
    };
    const handleMapClick = (e) => {
        const { lng, lat } = e.lngLat;
        setLatitude(lat);
        setLongitude(lng);
        fetchAnomalies(lat, lng);
        fetchPollutionData(lat, lng);
    };

    // Heatmap Layer Tanımı
    const heatmapLayer = {
        id: 'heatmap',
        type: 'heatmap',
        paint: {
            'heatmap-weight': ['interpolate', ['linear'], ['get', 'intensity'], 0, 0, 1, 1],
            'heatmap-intensity': ['interpolate', ['linear'], ['zoom'], 0, 1, 9, 3],
            'heatmap-color': [
                'interpolate',
                ['linear'],
                ['heatmap-density'],
                0, 'rgba(0, 0, 255, 0)',
                0.2, 'rgb(0, 255, 0)',
                0.4, 'rgb(255, 255, 0)',
                0.6, 'rgb(255, 165, 0)',
                0.8, 'rgb(255, 0, 0)',
                1, 'rgb(139, 0, 0)'
            ],
            'heatmap-radius': ['interpolate', ['linear'], ['zoom'], 0, 5, 9, 20],
            'heatmap-opacity': 0.7
        }
    };

    return (
        <div style={{ position: 'relative', height: '100vh' }}>
            {loading && (
                <div style={{ position: 'absolute', top: '10px', left: '10px', zIndex: 1, background: 'white', padding: '5px' }}>
                    Yükleniyor...
                </div>
            )}
            {error && (
                <div style={{ position: 'absolute', top: '10px', left: '10px', zIndex: 1, background: 'red', color: 'white', padding: '5px' }}>
                    {error}
                </div>
            )}

            <ReactMapGL
                {...viewport}
                onMoveEnd={(evt) => setViewport({
                    ...evt.viewState,
                    bounds: calculateMapBounds(evt.target)
                })}
                onClick={handleMapClick}
                mapStyle="mapbox://styles/mapbox/dark-v10"
                mapboxAccessToken={process.env.REACT_APP_MAPBOX_TOKEN}
            >
                <Source type="geojson" data={heatmapData}>
                    <Layer {...heatmapLayer} />
                </Source>

                {popupInfo && (
                    <Popup
                        latitude={popupInfo.latitude}
                        longitude={popupInfo.longitude}
                        closeButton={true}
                        closeOnClick={false}
                        onClose={() => setPopupInfo(null)}
                    >
                        <div>
                            <h3>Kirlilik Verisi</h3>
                            <p>PM2.5: {popupInfo.pm25} µg/m³</p>
                            <p>PM10: {popupInfo.pm10} µg/m³</p>
                            <p>NO2: {popupInfo.no2} µg/m³</p>
                            <p>SO2: {popupInfo.so2} µg/m³</p>
                            <p>O3: {popupInfo.o3} µg/m³</p>
                        </div>
                    </Popup>
                )}
            </ReactMapGL>
        </div>
    );
};

export default HeatMap;
