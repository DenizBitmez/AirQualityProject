import React from 'react';
import { Wind, CloudRain, Sun, AlertTriangle, ArrowRight } from 'lucide-react';
import '../App.css';

const Home = () => {
    return (
        <div className="min-h-screen bg-gradient-to-b from-blue-50 to-blue-100">
            {/* Navigation Bar */}
            <nav className="bg-white shadow-md">
                <div className="container mx-auto px-6 py-3 flex justify-between items-center">
                    <div className="flex items-center">
                        <Wind className="h-8 w-8 text-blue-500" />
                        <span className="ml-2 text-xl font-bold text-gray-800">AirQuality</span>
                    </div>
                </div>
            </nav>

            {/* Hero Section */}
            <div className="container mx-auto px-6 py-16">
                <div className="flex flex-col md:flex-row items-center">
                    <div className="md:w-1/2 mb-10 md:mb-0">
                        <h1 className="text-4xl md:text-5xl font-bold text-gray-800 leading-tight mb-6">
                            Temiz Hava, Sağlıklı Yaşam
                        </h1>
                        <p className="text-lg text-gray-600 mb-8">
                            Bulunduğunuz bölgedeki hava kalitesini gerçek zamanlı olarak takip edin ve sağlığınızı koruyun.
                        </p>
                        <div className="flex space-x-4">
                            <a
                                href="/dashboard"
                                className="px-6 py-3 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition duration-300 flex items-center"
                            >
                                Dashboard&apos;a Git
                                <ArrowRight className="ml-2 h-5 w-5" />
                            </a>
                            <a
                                href="#features"
                                className="px-6 py-3 bg-white border border-blue-500 text-blue-500 rounded-lg hover:bg-blue-50 transition duration-300"
                            >
                                Daha Fazla Bilgi
                            </a>
                        </div>
                    </div>
                    <div className="md:w-1/2 flex justify-center">
                        <div className="w-full max-w-md bg-white p-8 rounded-2xl shadow-xl">
                            <div className="text-center mb-6">
                                <h2 className="text-2xl font-bold text-gray-800">Güncel Hava Kalitesi</h2>
                                <p className="text-gray-500">İstanbul, Türkiye</p>
                            </div>
                            <div className="flex justify-center mb-6">
                                <div className="flex items-center justify-center w-32 h-32 bg-green-100 rounded-full">
                                    <span className="text-4xl font-bold text-green-600">68</span>
                                </div>
                            </div>
                            <p className="text-center text-lg font-medium text-green-600 mb-4">İYİ</p>
                            <div className="grid grid-cols-3 gap-4 text-center">
                                <div className="p-2">
                                    <p className="text-gray-500 text-sm">Sıcaklık</p>
                                    <div className="flex items-center justify-center mt-1">
                                        <Sun className="h-4 w-4 text-yellow-500 mr-1" />
                                        <p className="font-medium">24°C</p>
                                    </div>
                                </div>
                                <div className="p-2">
                                    <p className="text-gray-500 text-sm">Nem</p>
                                    <div className="flex items-center justify-center mt-1">
                                        <CloudRain className="h-4 w-4 text-blue-500 mr-1" />
                                        <p className="font-medium">45%</p>
                                    </div>
                                </div>
                                <div className="p-2">
                                    <p className="text-gray-500 text-sm">PM2.5</p>
                                    <div className="flex items-center justify-center mt-1">
                                        <AlertTriangle className="h-4 w-4 text-yellow-500 mr-1" />
                                        <p className="font-medium">14 µg/m³</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            {/* Features Section */}
            <div id="features" className="bg-white py-16">
                <div className="container mx-auto px-6">
                    <h2 className="text-3xl font-bold text-center text-gray-800 mb-12">Özelliklerimiz</h2>
                    <div className="grid md:grid-cols-3 gap-8">
                        <div className="bg-blue-50 p-6 rounded-xl shadow-sm hover:shadow-md transition duration-300">
                            <div className="bg-blue-100 w-12 h-12 rounded-full flex items-center justify-center mb-4">
                                <Wind className="h-6 w-6 text-blue-600" />
                            </div>
                            <h3 className="text-xl font-semibold text-gray-800 mb-2">Gerçek Zamanlı Veriler</h3>
                            <p className="text-gray-600">En güncel sensör verilerimizle bulunduğunuz bölgenin hava kalitesini anlık olarak takip edin.</p>
                        </div>
                        <div className="bg-blue-50 p-6 rounded-xl shadow-sm hover:shadow-md transition duration-300">
                            <div className="bg-blue-100 w-12 h-12 rounded-full flex items-center justify-center mb-4">
                                <AlertTriangle className="h-6 w-6 text-yellow-600" />
                            </div>
                            <h3 className="text-xl font-semibold text-gray-800 mb-2">Uyarı Sistemi</h3>
                            <p className="text-gray-600">Hava kalitesi düştüğünde anında bildirim alın ve gerekli önlemleri zamanında alın.</p>
                        </div>
                        <div className="bg-blue-50 p-6 rounded-xl shadow-sm hover:shadow-md transition duration-300">
                            <div className="bg-blue-100 w-12 h-12 rounded-full flex items-center justify-center mb-4">
                                <Sun className="h-6 w-6 text-orange-500" />
                            </div>
                            <h3 className="text-xl font-semibold text-gray-800 mb-2">Hava Tahmini</h3>
                            <p className="text-gray-600">Günlük, haftalık ve aylık hava kalitesi tahminlerimizle geleceği planlayın.</p>
                        </div>
                    </div>
                </div>
            </div>

            {/* Call to Action */}
            <div className="bg-blue-600 py-16">
                <div className="container mx-auto px-6 text-center">
                    <h2 className="text-3xl font-bold text-white mb-4">Hava Kalitesini Hemen Kontrol Edin</h2>
                    <p className="text-xl text-blue-100 mb-8">Sağlıklı nefes almak için ilk adımı atın</p>
                    <a
                        href="/dashboard"
                        className="px-8 py-4 bg-white text-blue-600 rounded-lg hover:bg-blue-50 transition duration-300 font-medium text-lg inline-flex items-center"
                    >
                        Dashboard&apos;a Git
                        <ArrowRight className="ml-2 h-5 w-5" />
                    </a>
                </div>
            </div>

            {/* Footer */}
            <footer className="bg-gray-800 py-12">
                <div className="container mx-auto px-6">
                    <div className="flex flex-col md:flex-row justify-between">
                        <div className="mb-6 md:mb-0">
                            <div className="flex items-center">
                                <Wind className="h-8 w-8 text-blue-400" />
                                <span className="ml-2 text-xl font-bold text-white">AirQuality</span>
                            </div>
                            <p className="mt-4 text-gray-400 max-w-md">
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