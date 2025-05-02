import React, { useEffect, useState, useCallback } from 'react';
import ReactMapGL, { Source, Layer } from 'react-map-gl';
import 'mapbox-gl/dist/mapbox-gl.css';

const HeatMap = () => {
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
            setError(null);  // Hata mesajını sıfırlıyoruz
            try {
                const { minLat, maxLat, minLon, maxLon } = viewport.bounds;
                const gridSize = calculateGridSize(Math.floor(viewport.zoom));
                const zoomLevel = Math.floor(viewport.zoom);

                const url = `${process.env.REACT_APP_API_BASE_URL}/api/quality/heatmap?` +
                    `minLat=${minLat}&maxLat=${maxLat}&` +
                    `minLon=${minLon}&maxLon=${maxLon}&` +
                    `gridSize=${gridSize}&zoomLevel=${zoomLevel}`;

                console.log('İstek URL:', url); // URL'yi logla
                const response = await fetch(url);
                console.log('Sunucu yanıtı:', response.status, response.statusText); // Sunucu yanıtını logla

                if (!response.ok) {
                    const errorData = await response.json().catch(() => null);
                    throw new Error(errorData?.message || `HTTP error! status: ${response.status}`);
                }

                const data = await response.json();
                console.log('Alınan veri:', data);

                if (!data || !Array.isArray(data.features)) {
                    throw new Error("Geçersiz veri formatı");
                }

                setHeatmapData(data);
            } catch (err) {
                console.error("Detaylı API Hatası:", {
                    message: err.message,
                    stack: err.stack,
                    response: err.response
                });
                setError(`Hata: ${err.message}`);
                setLoading(false);
            }
        };

        const debounceTimer = setTimeout(fetchData, 500); // 500ms debounce
        return () => clearTimeout(debounceTimer);
    }, [viewport.bounds, viewport.zoom]);

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
                mapStyle="mapbox://styles/mapbox/dark-v10"
                mapboxAccessToken={process.env.REACT_APP_MAPBOX_TOKEN}
            >
                <Source type="geojson" data={heatmapData}>
                    <Layer {...heatmapLayer} />
                </Source>
            </ReactMapGL>
        </div>
    );
};

export default HeatMap;
