import React from 'react';
import { Wind, CloudRain, Sun, AlertTriangle, ArrowRight } from 'lucide-react';

const Home = () => {
    return (
        <div style={{ minHeight: '100vh', background: 'linear-gradient(to bottom, #f0f8ff, #e6f2ff)' }}>
            <nav className="nav">
                <div className="nav-container">
                    <div className="nav-logo">
                        <Wind style={{ height: '2rem', width: '2rem', color: '#3182ce', marginRight: '0.5rem' }} />
                        <span>AirQuality</span>
                    </div>
                </div>
            </nav>

            <div className="container" style={{ paddingTop: '4rem', paddingBottom: '4rem' }}>
                <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', gap: '3rem' }}>
                    <div style={{ textAlign: 'center', maxWidth: '800px' }}>
                        <h1 style={{
                            fontSize: '3rem',
                            fontWeight: 'bold',
                            color: '#2d3748',
                            lineHeight: '1.2',
                            marginBottom: '1.5rem'
                        }}>
                            Temiz Hava, Sağlıklı Yaşam
                        </h1>
                        <p style={{
                            fontSize: '1.25rem',
                            color: '#4a5568',
                            marginBottom: '2rem',
                            lineHeight: '1.6'
                        }}>
                            Bulunduğunuz bölgedeki hava kalitesini gerçek zamanlı olarak takip edin ve sağlığınızı koruyun.
                        </p>
                        <div style={{ display: 'flex', gap: '1rem', justifyContent: 'center', flexWrap: 'wrap' }}>
                            <a
                                href="/dashboard"
                                style={{
                                    display: 'inline-flex',
                                    alignItems: 'center',
                                    padding: '0.75rem 1.5rem',
                                    backgroundColor: '#3182ce',
                                    color: 'white',
                                    borderRadius: '0.5rem',
                                    textDecoration: 'none',
                                    transition: 'background-color 0.3s ease',
                                    fontWeight: '500'
                                }}
                                onMouseEnter={(e) => e.target.style.backgroundColor = '#2c5aa0'}
                                onMouseLeave={(e) => e.target.style.backgroundColor = '#3182ce'}
                            >
                                Dashboard'a Git
                                <ArrowRight style={{ marginLeft: '0.5rem', height: '1.25rem', width: '1.25rem' }} />
                            </a>
                            <a
                                href="#features"
                                style={{
                                    display: 'inline-flex',
                                    alignItems: 'center',
                                    padding: '0.75rem 1.5rem',
                                    backgroundColor: 'white',
                                    color: '#3182ce',
                                    border: '2px solid #3182ce',
                                    borderRadius: '0.5rem',
                                    textDecoration: 'none',
                                    transition: 'background-color 0.3s ease',
                                    fontWeight: '500'
                                }}
                                onMouseEnter={(e) => e.target.style.backgroundColor = '#ebf8ff'}
                                onMouseLeave={(e) => e.target.style.backgroundColor = 'white'}
                            >
                                Daha Fazla Bilgi
                            </a>
                        </div>
                    </div>

                    <div className="air-quality-card" style={{ maxWidth: '400px', width: '100%' }}>
                        <div style={{ textAlign: 'center', marginBottom: '1.5rem' }}>
                            <h2 style={{ fontSize: '1.5rem', fontWeight: 'bold', color: '#2d3748', marginBottom: '0.5rem' }}>
                                Güncel Hava Kalitesi
                            </h2>
                            <p style={{ color: '#718096' }}>İstanbul, Türkiye</p>
                        </div>

                        <div style={{ display: 'flex', justifyContent: 'center', marginBottom: '1.5rem' }}>
                            <div style={{
                                display: 'flex',
                                alignItems: 'center',
                                justifyContent: 'center',
                                width: '8rem',
                                height: '8rem',
                                backgroundColor: '#c6f6d5',
                                borderRadius: '50%'
                            }}>
                                <span style={{ fontSize: '2.5rem', fontWeight: 'bold', color: '#38a169' }}>68</span>
                            </div>
                        </div>

                        <p style={{
                            textAlign: 'center',
                            fontSize: '1.25rem',
                            fontWeight: '600',
                            color: '#38a169',
                            marginBottom: '1rem'
                        }}>
                            İYİ
                        </p>

                        <div style={{
                            display: 'grid',
                            gridTemplateColumns: 'repeat(3, 1fr)',
                            gap: '1rem',
                            textAlign: 'center'
                        }}>
                            <div>
                                <p style={{ color: '#718096', fontSize: '0.875rem', marginBottom: '0.5rem' }}>Sıcaklık</p>
                                <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                                    <Sun style={{ height: '1rem', width: '1rem', color: '#f6ad55', marginRight: '0.25rem' }} />
                                    <p style={{ fontWeight: '500' }}>24°C</p>
                                </div>
                            </div>
                            <div>
                                <p style={{ color: '#718096', fontSize: '0.875rem', marginBottom: '0.5rem' }}>Nem</p>
                                <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                                    <CloudRain style={{ height: '1rem', width: '1rem', color: '#4299e1', marginRight: '0.25rem' }} />
                                    <p style={{ fontWeight: '500' }}>45%</p>
                                </div>
                            </div>
                            <div>
                                <p style={{ color: '#718096', fontSize: '0.875rem', marginBottom: '0.5rem' }}>PM2.5</p>
                                <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                                    <AlertTriangle style={{ height: '1rem', width: '1rem', color: '#f6ad55', marginRight: '0.25rem' }} />
                                    <p style={{ fontWeight: '500' }}>14 µg/m³</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="features" style={{ backgroundColor: 'white', padding: '4rem 0' }}>
                <div className="container">
                    <h2 style={{
                        fontSize: '2rem',
                        fontWeight: 'bold',
                        textAlign: 'center',
                        color: '#2d3748',
                        marginBottom: '3rem'
                    }}>
                        Özelliklerimiz
                    </h2>
                    <div className="features-grid">
                        <div className="feature-card">
                            <div className="feature-icon">
                                <Wind style={{ height: '1.5rem', width: '1.5rem', color: '#3182ce' }} />
                            </div>
                            <h3 className="feature-title">Gerçek Zamanlı Veriler</h3>
                            <p className="feature-description">
                                En güncel sensör verilerimizle bulunduğunuz bölgenin hava kalitesini anlık olarak takip edin.
                            </p>
                        </div>
                        <div className="feature-card">
                            <div className="feature-icon">
                                <AlertTriangle style={{ height: '1.5rem', width: '1.5rem', color: '#f6ad55' }} />
                            </div>
                            <h3 className="feature-title">Uyarı Sistemi</h3>
                            <p className="feature-description">
                                Hava kalitesi düştüğünde anında bildirim alın ve gerekli önlemleri zamanında alın.
                            </p>
                        </div>
                        <div className="feature-card">
                            <div className="feature-icon">
                                <Sun style={{ height: '1.5rem', width: '1.5rem', color: '#f6ad55' }} />
                            </div>
                            <h3 className="feature-title">Hava Tahmini</h3>
                            <p className="feature-description">
                                Günlük, haftalık ve aylık hava kalitesi tahminlerimizle geleceği planlayın.
                            </p>
                        </div>
                    </div>
                </div>
            </div>

            <div style={{ backgroundColor: '#3182ce', padding: '4rem 0' }}>
                <div className="container" style={{ textAlign: 'center' }}>
                    <h2 style={{
                        fontSize: '2rem',
                        fontWeight: 'bold',
                        color: 'white',
                        marginBottom: '1rem'
                    }}>
                        Hava Kalitesini Hemen Kontrol Edin
                    </h2>
                    <p style={{
                        fontSize: '1.25rem',
                        color: '#bee3f8',
                        marginBottom: '2rem'
                    }}>
                        Sağlıklı nefes almak için ilk adımı atın
                    </p>
                    <a
                        href="/dashboard"
                        className="btn btn-white"
                        style={{
                            display: 'inline-flex',
                            alignItems: 'center',
                            fontSize: '1.125rem'
                        }}
                    >
                        Dashboard'a Git
                        <ArrowRight style={{ marginLeft: '0.5rem', height: '1.25rem', width: '1.25rem' }} />
                    </a>
                </div>
            </div>

            <footer style={{ backgroundColor: '#2d3748', padding: '3rem 0' }}>
                <div className="container">
                    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', textAlign: 'center' }}>
                        <div style={{ marginBottom: '1.5rem' }}>
                            <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', marginBottom: '1rem' }}>
                                <Wind style={{ height: '2rem', width: '2rem', color: '#63b3ed', marginRight: '0.5rem' }} />
                                <span style={{ fontSize: '1.25rem', fontWeight: 'bold', color: 'white' }}>AirQuality</span>
                            </div>
                            <p style={{
                                color: '#a0aec0',
                                maxWidth: '500px',
                                lineHeight: '1.6'
                            }}>
                                Hava kalitesi verilerini izlemenize ve sağlığınızı korumanıza yardımcı olan güvenilir platformunuz.
                            </p>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    );
};

export default Home;