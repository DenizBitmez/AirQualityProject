import React, { useEffect, useState } from "react";
import { Line } from "react-chartjs-2";
import axios from "axios";

const PollutionChart = ({ latitude, longitude }) => {
    const [chartData, setChartData] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get("http://localhost:8080/api/quality/region/pollution", {
                    params: { latitude, longitude },
                });

                const data = response.data.data || [];

                const timestamps = data.map((item) =>
                    item.timestamp
                        ? new Date(item.timestamp).toLocaleString('tr-TR', {
                            hour: '2-digit',
                            minute: '2-digit',
                            day: '2-digit',
                            month: '2-digit'
                        })
                        : "Tarih Yok"
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
                            backgroundColor: "rgba(255, 99, 132, 0.2)",
                            fill: false,
                            tension: 0.2,
                        },
                        {
                            label: "PM10",
                            data: pm10,
                            borderColor: "rgba(54, 162, 235, 1)",
                            backgroundColor: "rgba(54, 162, 235, 0.2)",
                            fill: false,
                            tension: 0.2,
                        },
                    ],
                });
            } catch (error) {
                console.error("Kirlilik verisi alınırken hata:", error);
            }
        };

        if (latitude && longitude) {
            fetchData();
        }
    }, [latitude, longitude]);

    const options = {
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: 'Zamana Göre PM2.5 ve PM10 Değerleri',
            },
        },
        scales: {
            x: {
                title: {
                    display: true,
                    text: 'Zaman',
                },
            },
            y: {
                title: {
                    display: true,
                    text: 'µg/m³',
                },
                beginAtZero: true,
            },
        },
    };

    if (!chartData) return <div>Yükleniyor...</div>;

    if (chartData.labels.length === 0) {
        return <div>Bu konum için veri bulunamadı.</div>;
    }

    return (
        <div>
            <h3>Kirlilik Seviyeleri Zaman Grafiği</h3>
            <Line data={chartData} options={options} />
        </div>
    );
};

export default PollutionChart;
