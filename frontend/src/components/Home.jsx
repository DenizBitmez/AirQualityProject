import React from 'react';
import { Wind, CloudRain, Sun, AlertTriangle, ArrowRight } from 'lucide-react';

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
                    <div className="flex space-x-4">
                        <a href="#" className="py-2 px-4 text-gray-700 font-medium hover:text-blue-500">Ana Sayfa</a>
                        <a href="#" className="py-2 px-4 text-gray-700 font-medium hover:text-blue-500">Hakkımızda</a>
                        <a href="#" className="py-2 px-4 text-gray-700 font-medium hover:text-blue-500">İletişim</a>
                        <a href="/dashboard" className="py-2 px-4 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition duration-300 font-medium">
                            Dashboard
                        </a>
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
                        <div className="grid grid-cols-2 md:grid-cols-3 gap-8">
                            <div>
                                <h3 className="text-white font-semibold mb-4">Platform</h3>
                                <ul className="space-y-2 text-gray-400">
                                    <li><a href="#" className="hover:text-white transition duration-300">Ana Sayfa</a></li>
                                    <li><a href="/dashboard" className="hover:text-white transition duration-300">Dashboard</a></li>
                                    <li><a href="#" className="hover:text-white transition duration-300">Harita</a></li>
                                </ul>
                            </div>
                            <div>
                                <h3 className="text-white font-semibold mb-4">Hakkımızda</h3>
                                <ul className="space-y-2 text-gray-400">
                                    <li><a href="#" className="hover:text-white transition duration-300">Misyonumuz</a></li>
                                    <li><a href="#" className="hover:text-white transition duration-300">Ekibimiz</a></li>
                                    <li><a href="#" className="hover:text-white transition duration-300">Kariyer</a></li>
                                </ul>
                            </div>
                            <div>
                                <h3 className="text-white font-semibold mb-4">İletişim</h3>
                                <ul className="space-y-2 text-gray-400">
                                    <li><a href="#" className="hover:text-white transition duration-300">Destek</a></li>
                                    <li><a href="#" className="hover:text-white transition duration-300">İletişim</a></li>
                                    <li><a href="#" className="hover:text-white transition duration-300">SSS</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div className="border-t border-gray-700 mt-8 pt-8 flex flex-col md:flex-row justify-between items-center">
                        <p className="text-gray-400">© 2025 AirQuality. Tüm hakları saklıdır.</p>
                        <div className="flex space-x-4 mt-4 md:mt-0">
                            <a href="#" className="text-gray-400 hover:text-white transition duration-300">
                                <span className="sr-only">Facebook</span>
                                <svg className="h-6 w-6" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true">
                                    <path fillRule="evenodd" d="M22 12c0-5.523-4.477-10-10-10S2 6.477 2 12c0 4.991 3.657 9.128 8.438 9.878v-6.987h-2.54V12h2.54V9.797c0-2.506 1.492-3.89 3.777-3.89 1.094 0 2.238.195 2.238.195v2.46h-1.26c-1.243 0-1.63.771-1.63 1.562V12h2.773l-.443 2.89h-2.33v6.988C18.343 21.128 22 16.991 22 12z" clipRule="evenodd" />
                                </svg>
                            </a>
                            <a href="#" className="text-gray-400 hover:text-white transition duration-300">
                                <span className="sr-only">Twitter</span>
                                <svg className="h-6 w-6" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true">
                                    <path d="M8.29 20.251c7.547 0 11.675-6.253 11.675-11.675 0-.178 0-.355-.012-.53A8.348 8.348 0 0022 5.92a8.19 8.19 0 01-2.357.646 4.118 4.118 0 001.804-2.27 8.224 8.224 0 01-2.605.996 4.107 4.107 0 00-6.993 3.743 11.65 11.65 0 01-8.457-4.287 4.106 4.106 0 001.27 5.477A4.072 4.072 0 012.8 9.713v.052a4.105 4.105 0 003.292 4.022 4.095 4.095 0 01-1.853.07 4.108 4.108 0 003.834 2.85A8.233 8.233 0 012 18.407a11.616 11.616 0 006.29 1.84" />
                                </svg>
                            </a>
                            <a href="#" className="text-gray-400 hover:text-white transition duration-300">
                                <span className="sr-only">Instagram</span>
                                <svg className="h-6 w-6" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true">
                                    <path fillRule="evenodd" d="M12.315 2c2.43 0 2.784.013 3.808.06 1.064.049 1.791.218 2.427.465a4.902 4.902 0 011.772 1.153 4.902 4.902 0 011.153 1.772c.247.636.416 1.363.465 2.427.048 1.067.06 1.407.06 4.123v.08c0 2.643-.012 2.987-.06 4.043-.049 1.064-.218 1.791-.465 2.427a4.902 4.902 0 01-1.153 1.772 4.902 4.902 0 01-1.772 1.153c-.636.247-1.363.416-2.427.465-1.067.048-1.407.06-4.123.06h-.08c-2.643 0-2.987-.012-4.043-.06-1.064-.049-1.791-.218-2.427-.465a4.902 4.902 0 01-1.772-1.153 4.902 4.902 0 01-1.153-1.772c-.247-.636-.416-1.363-.465-2.427-.047-1.024-.06-1.379-.06-3.808v-.63c0-2.43.013-2.784.06-3.808.049-1.064.218-1.791.465-2.427a4.902 4.902 0 011.153-1.772A4.902 4.902 0 015.45 2.525c.636-.247 1.363-.416 2.427-.465C8.901 2.013 9.256 2 11.685 2h.63zm-.081 1.802h-.468c-2.456 0-2.784.011-3.807.058-.975.045-1.504.207-1.857.344-.467.182-.8.398-1.15.748-.35.35-.566.683-.748 1.15-.137.353-.3.882-.344 1.857-.047 1.023-.058 1.351-.058 3.807v.468c0 2.456.011 2.784.058 3.807.045.975.207 1.504.344 1.857.182.466.399.8.748 1.15.35.35.683.566 1.15.748.353.137.882.3 1.857.344 1.054.048 1.37.058 4.041.058h.08c2.597 0 2.917-.01 3.96-.058.976-.045 1.505-.207 1.858-.344.466-.182.8-.398 1.15-.748.35-.35.566-.683.748-1.15.137-.353.3-.882.344-1.857.048-1.055.058-1.37.058-4.041v-.08c0-2.597-.01-2.917-.058-3.96-.045-.976-.207-1.505-.344-1.858a3.097 3.097 0 00-.748-1.15 3.098 3.098 0 00-1.15-.748c-.353-.137-.882-.3-1.857-.344-1.023-.047-1.351-.058-3.807-.058zM12 6.865a5.135 5.135 0 110 10.27 5.135 5.135 0 010-10.27zm0 1.802a3.333 3.333 0 100 6.666 3.333 3.333 0 000-6.666zm5.338-3.205a1.2 1.2 0 110 2.4 1.2 1.2 0 010-2.4z" clipRule="evenodd" />
                                </svg>
                            </a>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    );
};

export default Home;