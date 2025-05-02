import React from 'react';
import { Link } from 'react-router-dom';

const Home = () => {
    return (
        <div>
            <h1>Ana Sayfa</h1>
            <Link to="/dashboard">Dashboard'a Git</Link>
        </div>
    );
};

export default Home;
