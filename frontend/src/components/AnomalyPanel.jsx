import React, { useEffect, useState } from "react";
import axios from "axios";

const AnomalyPanel = ({ latitude, longitude }) => {
    const [anomalies, setAnomalies] = useState([]);

    useEffect(() => {
        const fetchAnomalies = async () => {
            try {
                const response = await axios.get("http://localhost:8080/api/quality/regional-anomalies", {
                    params: {
                        lat: latitude,
                        lon: longitude,
                        radiusKm: 25,
                    },
                });

                if (response.data) {
                    setAnomalies(response.data.anomalies);
                }
            } catch (error) {
                console.error("Anomali verisi alınırken hata:", error);
            }
        };

        if (latitude && longitude) {
            fetchAnomalies();
        }
    }, [latitude, longitude]);

    return (
        <div style={{ border: "1px solid red", padding: "10px", marginTop: "20px" }}>
            <h3>Anormal Değerler</h3>
            {anomalies.length === 0 ? (
                <p>Bu bölgede anormal değer bulunamadı.</p>
            ) : (
                <ul>
                    {anomalies.map((item, idx) => (
                        <li key={idx}>
                            📍 {item.location} - {item.parameter}: {item.value}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default AnomalyPanel;
