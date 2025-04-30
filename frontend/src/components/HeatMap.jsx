import React, { useEffect, useState } from 'react';
import ReactMapGL, { Source, Layer } from 'react-map-gl';
import 'mapbox-gl/dist/mapbox-gl.css';

const HeatMap = () => {
    const [pollutionData, setPollutionData] = useState({
        type: 'FeatureCollection',
        features: [],
    });

    useEffect(() => {
        fetch('${process.env.REACT_APP_API_BASE_URL}/api/quality/all')
            .then((response) => response.json())
            .then((res) => {
                const data = Array.isArray(res.data) ? res.data : res;
                if (!Array.isArray(data) || data.length === 0) {
                    console.error('Veri boş veya geçersiz!', data);
                    return;
                }

                const features = data
                    .map((item) => {
                        const lng = Number(item.longitude);
                        const lat = Number(item.latitude);
                        if (isNaN(lng) || isNaN(lat)) return null;

                        return {
                            type: 'Feature',
                            properties: {
                                pm25: item.pm25 ?? 0, // fallback
                            },
                            geometry: {
                                type: 'Point',
                                coordinates: [lng, lat],
                            },
                        };
                    })
                    .filter(Boolean);

                if (features.length === 1 && Array.isArray(features[0].geometry.coordinates)) {
                    const original = features[0].geometry.coordinates;
                    if (original.length === 2) {
                        features.push({
                            type: 'Feature',
                            properties: { pm25: 0 },
                            geometry: {
                                type: 'Point',
                                coordinates: [original[0] + 0.001, original[1] + 0.001],
                            },
                        });
                    }
                }

                setPollutionData({
                    type: 'FeatureCollection',
                    features: features,
                });
            })
            .catch((error) => console.error('Error fetching data:', error));
    }, []);

    const heatmapLayer = {
        id: 'heatmap',
        type: 'heatmap',
        source: 'pollution',
        maxzoom: 9,
        paint: {
            'heatmap-weight': [
                'interpolate',
                ['linear'],
                ['get', 'pm25'],
                0, 0,
                100, 1,
            ],
            'heatmap-intensity': [
                'interpolate',
                ['linear'],
                ['zoom'],
                0, 1,
                9, 3,
            ],
            'heatmap-color': [
                'interpolate',
                ['linear'],
                ['heatmap-density'],
                0, 'blue',
                0.5, 'lime',
                1, 'red',
            ],
            'heatmap-radius': [
                'interpolate',
                ['linear'],
                ['zoom'],
                0, 2,
                9, 20,
            ],
            'heatmap-opacity': [
                'interpolate',
                ['linear'],
                ['zoom'],
                7, 1,
                9, 0,
            ],
        },
    };

    return (
        <ReactMapGL
            initialViewState={{
                latitude: 38.925533,
                longitude: 33.866287,
                zoom: 5,
            }}
            style={{ width: '100%', height: '100vh' }}
            mapStyle="mapbox://styles/mapbox/streets-v11"
            mapboxAccessToken={process.env.REACT_APP_MAPBOX_TOKEN}
        >
            {pollutionData.features && pollutionData.features.length > 0 && (
                <Source id="pollution" type="geojson" data={pollutionData}>
                    <Layer {...heatmapLayer} />
                </Source>
            )}
        </ReactMapGL>
    );
};

export default HeatMap;