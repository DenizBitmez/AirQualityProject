import React from 'react';
import HeatMap from './HeatMap';
import PollutionChart from './PollutionChart';

const Dashboard = () => {
    return (
        <div className="dashboard-container">
            <h2>Hava Kirliliği Dashboard</h2>
            <HeatMap />
            <PollutionChart latitude={39.925533} longitude={33.866287} />
        </div>
    );
};

export default Dashboard;