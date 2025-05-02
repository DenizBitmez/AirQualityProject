import React, { useEffect, useState } from "react";
import { Line } from "react-chartjs-2";
import axios from "axios";
import {
    Chart as ChartJS,
    LineElement,
    PointElement,
    CategoryScale,
    LinearScale,
    Tooltip,
    Title,
    Legend,
} from "chart.js";

ChartJS.register(LineElement, PointElement, CategoryScale, LinearScale, Title, Tooltip, Legend);

const PollutionChart = ({ latitude, longitude }) => {
    const [chartData, setChartData] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            const response = await axios.get("http://localhost:8080/api/quality/region/pollution", {
                params: {
                  latitude,longitude
                }
            });
            const data = response.data.data;

            const timestamps = data.map((item) =>
                item.timestamp ? new Date(item.timestamp).toLocaleString() : "Tarih Yok"
            );

            const pm25 = data.map((item) => item.pm25);
            const pm10 = data.map((item) => item.pm10);

            setChartData({
                labels: timestamps,
                datasets: [
                    {
                        label: "PM2.5",
                        data: pm25,
                        borderColor: "rgba(255, 99, 132, 1)",
                        fill: false,
                    },
                    {
                        label: "PM10",
                        data: pm10,
                        borderColor: "rgba(54, 162, 235, 1)",
                        fill: false,
                    },
                ],
            });
        };

        fetchData();
    }, [latitude, longitude]);

    if (!chartData) return <div>Yükleniyor...</div>;

    return (
        <div>
            <h3>Kirlilik Seviyeleri Zaman Grafiği</h3>
            <Line data={chartData} />
        </div>
    );
};

export default PollutionChart;
