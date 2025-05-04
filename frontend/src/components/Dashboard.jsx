import axios from "axios";
import {useState} from "react";
import HeatMap from "./HeatMap";
import PollutionChart from "./PollutionChart";
import AnomalyPanel from "./AnomalyPanel";

const Dashboard = () => {
    const [latitude, setLatitude] = useState(39.925533);
    const [longitude, setLongitude] = useState(33.866287);
    const [pollutionData, setPollutionData] = useState(null);
    const [anomalies, setAnomalies] = useState([]);

    const fetchPollutionData = async (lat, lon) => {
        try {
            const response = await axios.get("http://localhost:8080/api/quality/region/pollution", {
                params: { latitude: lat, longitude: lon }
            });
            setPollutionData(response.data.data);
        } catch (error) {
            console.error("Error fetching pollution data", error);
        }
    };

    const fetchAnomalies = async (lat, lon) => {
        try {
            const response = await axios.get("http://localhost:8080/api/quality/regional-anomalies", {
                params: { lat, lon, radiusKm: 25 }
            });
            setAnomalies(response.data);
        } catch (error) {
            console.error("Error fetching anomalies", error);
        }
    };

    const handleRegionSelect = (lat, lon) => {
        setLatitude(lat);
        setLongitude(lon);
        fetchPollutionData(lat, lon);
        fetchAnomalies(lat, lon);
    };

    return (
        <div className="dashboard-container">
            <h2>Hava Kirliliği Dashboard</h2>
            <div className="grid-layout">
                <div className="map-section">
                    <HeatMap setAnomalies={setAnomalies} fetchPollutionData={fetchPollutionData} setLatitude={setLatitude}
                             setLongitude={setLongitude}/>
                </div>
                <div className="chart-section">
                    <PollutionChart latitude={latitude} longitude={longitude} pollutionData={pollutionData} />
                    <AnomalyPanel anomalies={anomalies} />
                </div>
            </div>
        </div>
    );
};
export default Dashboard;
