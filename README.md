
# **Gerçek Zamanlı Hava Kirliliği İzleme Platformu**

## **Projenin Tanımı**

Bu proje, Kartaca'nın Çekirdekten Yetişenler Programı için geliştirilmiş bir hava kirliliği izleme ve analiz platformudur. Platform, dünya genelindeki hava kalitesi verilerini gerçek zamanlı olarak toplayarak, analiz eder ve görselleştirir. Kullanıcılar, hava kirliliği seviyelerini anlık olarak takip edebilir, coğrafi bölgelerdeki kirlilik seviyelerini harita üzerinde görebilir ve olası anomali durumlarını tespit edebilirler.

Dünya genelinde hava kirliliği, sağlık üzerindeki olumsuz etkileriyle bilinen ciddi bir çevre sorunudur. Bu platform, kullanıcılara hava kalitesinin anlık durumunu göstermekle kalmaz, aynı zamanda geçmiş verilerle kıyaslama yaparak, tarihsel hava kirliliği analizi sunar. Veriler görselleştirildikçe, PM2.5, PM10 gibi kritik hava kirliliği parametreleriyle kullanıcıların bilgilendirilmesi sağlanır. Platform, gerçek zamanlı veri toplayan ve işleyen bir altyapıya sahiptir. Ayrıca, anomalik verileri tespit etmek ve kullanıcıları bilgilendirmek amacıyla entegre bir uyarı sistemi mevcuttur.

---

## **Platformun Temel Özellikleri:**

* **Gerçek Zamanlı Hava Kalitesi Veri Toplama**
* **Anomali Tespiti ve Kullanıcı Uyarı Sistemi**
* **Coğrafi Veri Görselleştirme**
* **Tarihsel Veri Analizi**

---

## **Sistem Mimarisi**

Sistem, monolitik bir yapı kullanılarak geliştirilmiştir. Bu, özellikle platformun hızla geliştirilmesi ve yönetilmesi için tercih edilmiştir. Ancak, mimari ilerleyen zamanlarda ölçeklenebilirliği artırmak amacıyla modüler hale getirilebilir. Sistem, Spring Boot tabanlı bir backend, React tabanlı bir frontend, TimescaleDB tabanlı veritabanı ve Apache Kafka kullanılarak veri akışlarını yönetir.

---

## **Kullanılan Teknolojiler:**

* **Java (Spring Boot):** Monolitik mimarisi, kolay konfigürasyon ve hızlı geliştirme için.
* **Apache Kafka:** Yüksek hacimli gerçek zamanlı veri işleme.
* **TimescaleDB:** Zaman serisi verilerde güçlü sorgulama ve performans.
* **React:** Component tabanlı yapı, modern SPA deneyimi.
* **Mapbox:** Etkileşimli harita üzerinde sensör konumu gösterimi.
* **Chart.js:** Hava kalitesi verilerini görsel olarak sunmak için.
* **Docker:** Tüm bileşenlerin konteynerize çalıştırılması için.
* **Axios:** REST API çağrıları için.
* **Shell Script (.sh):** Kurulum ve veri test otomasyonu.
* **Postman:** Her bir yapının çalışıp çalışmadığını kontrol etme.

---

## **Kurulum Adımları**

### **1. Ortam Gereksinimleri:**

* [Node.js](https://nodejs.org/en/) (v18.x veya üstü)
* [Java 17+](https://adoptopenjdk.net/)
* [PostgreSQL 15+](https://www.postgresql.org/)
* [Docker ve Docker Compose](https://www.docker.com/products/docker-desktop)

### **Veritabanı Kurulumu**

```
CREATE DATABASE air_quality;
```

### **Veritabanı Bağlantısı İçin:**

```
spring.datasource.url=jdbc:mysql://localhost:3306/air_quality
spring.datasource.username=root
spring.datasource.password=yourpassword
```

### **Backend:**

```
./mvnw clean install
./mvnw spring-boot:run
```

### **Frontend:**

İntellij üzerinden React uygulaması oluşturabilirsiniz. Öncelikle Frontend Directorysi oluşturmalısınız. Sonrasında:

```
cd frontend
npm install -g npm@latest
npx create-react-app .
```

### **.env**

Gerekli bağımlılıklarınız veya secret keylerinizi burada saklayabilirsiniz.

```
REACT_APP_MAPBOX_TOKEN=your-mapbox-token
REACT_APP_API_BASE_URL=http://localhost:8080
```

### **Dockerize:**

```
docker-compose up --build
```

---

## **Kullanım Rehberi**

* Uygulamaya giriş yapıldığında minimal bir giriş ekranı sizi karşılar.
* Uygulamanın amacına yönelik bilgiler bulunur.
* Hava kirlilik değerleri Dashboard menüsünde tutulur.
* Kirlilik derecesine göre burada bir ısı haritası Mapbox ile sağlanır.
* Haritadaki noktalara göre hava kirlilik grafiği ve değerleri (PM2.5, PM10) Chart.js ile verilir.
* Anomali paneli sayesinde haritada bulunan anomaliler görülebilir.

---

## **API Parametre Açıklamaları**

* `location`: Hava kalitesinin sorgulanacağı şehir adı (örneğin: "İstanbul")
* `lat` ve `lon`: Koordinatları kullanarak bir bölgenin hava kalitesini sorgulamak için kullanılır.
* `radiusKm`: Hava kalitesi verisi alınacak bölgenin yarıçapı (km cinsinden).
* `startDate` ve `endDate`: Tarih aralığı sorgusu yaparken kullanılacak ISO formatındaki tarihler.

---

## **API Dokümantasyonu (Spring Boot)**

### **Post Hava Kalitesi Ekleme:**

```
http://localhost:8080/api/quality/save

![quality1](https://github.com/user-attachments/assets/263c9c62-36bb-4a90-8cbc-5c1202316e80)

```

### **GET Tüm Hava Kalitesini Getirme:**

```
http://localhost:8080/api/quality/all

![image](https://github.com/user-attachments/assets/2934a766-84bf-42f5-a4bd-5a28fbd80a16)

```

### **GET Lokasyona Göre Hava Kalitesini Getirme:**

```
http://localhost:8080/api/quality/location?location=İstanbul

![quality2](https://github.com/user-attachments/assets/a886f3e3-5fed-4cb1-bf02-1be66b99d9f6)

```

### **GET Anomali Getirme:**

```
http://localhost:8080/api/quality/regional-anomalies?lat=39.925533&lon=31.866287&radiusKm=25

![image](https://github.com/user-attachments/assets/419279ea-ff7b-417d-bfc4-8a3a36f0ff6a)

```

### **GET Lokasyon ve Tarihe Göre Hava Kirliliği Getirme:**

```
http://localhost:8080/api/quality/anomaly/İzmir?startDate=2025-05-04T00:00:00Z&endDate=2025-05-04T23:59:59Z

![image](https://github.com/user-attachments/assets/924f2284-abf9-4b12-b44f-6bec680e749c)

```

### **GET Son 24 Saatte Girilen Konumun Hava Kalitesi:**

```
http://localhost:8080/api/quality/last-24-hours/İzmir

![image](https://github.com/user-attachments/assets/ce40878d-2f18-4782-a19c-5195f66b8ca1)

```

### **GET Enlem ve Boylama Göre Hava Kirliliğini Getirme:**

```
http://localhost:8080/api/quality/region/pollution?latitude=38.925533&longitude=31.866287

![image](https://github.com/user-attachments/assets/c67c7066-1417-4d09-8a6b-53c2ce03bdff)

```

### **GET Isı Haritasını Getirme:**

```
http://localhost:8080/api/quality/heatmap?minLat=35.0&maxLat=42.0&minLon=26.0&maxLon=45.0&zoomLevel=5

![image](https://github.com/user-attachments/assets/189afaa0-03f0-424b-b468-5906df7b1c3d)

```

---

## **Shell Script’ler**

### **scripts/auto-test.sh**

```
./auto-test.sh --duration=60 --rate=3 --anomaly-chance=30

![Ekran görüntüsü 2025-05-04 172006](https://github.com/user-attachments/assets/383cbed0-2927-4607-895e-fa6c90a9b07d)

```

### **scripts/manual-test.sh**

```
./manual-input.sh 40.7128 -74.0060 pm25 10

![Ekran görüntüsü 2025-05-04 170659](https://github.com/user-attachments/assets/cfd0b90e-5623-42e8-8aef-fed3e6c04dac)

```

---

## **Sorun Giderme Rehberi**

| Sorun                        | Çözüm                                                                                  |
| ---------------------------- | -------------------------------------------------------------------------------------- |
| `CORS hatası`                | Spring Boot `@CrossOrigin` veya global config ekleyin.                                 |
| `Mapbox yüklenmiyor`         | `VITE_MAPBOX_TOKEN` .env dosyasında tanımlı mı?                                        |
| `PostgreSQL bağlantı hatası` | Kullanıcı adı/parola doğru mu? Port 5432 açık mı?                                      |
| `npm run dev hata veriyor`   | Node versiyonunu kontrol edin. `rm -rf node_modules && npm install` ile yeniden kurun. |
| Docker konteyner açılmıyor   | `docker-compose down -v && up --build` ile yeniden deneyin.                            |

---

## **Geliştirici Notları**

* **Mapbox token** ücretsiz alınabilir: [https://account.mapbox.com/access-tokens](https://account.mapbox.com/access-tokens)
* **Chart.js** için responsive ayarlar `/components/Charts.jsx` dosyasında tanımlıdır.
* API call’ları `api/axiosInstance.js` üzerinden merkezi kontrol edilir.

---

## **Katkı Sağlama**

Bu projeye katkı sağlamak istiyorsanız, aşağıdaki adımları takip edebilirsiniz:

1. Bu projeyi forkladıktan sonra, kendi dalınızı oluşturun: `git checkout -b feature-xyz`
2. Değişikliklerinizi yapın ve commit edin: `git commit -am 'Add new feature xyz'`
3. Değişikliklerinizi main dalına göndermek için pull request oluşturun.

---

## **Lisans**

### **MIT License © 2025**

---
