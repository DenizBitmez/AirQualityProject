# **Real-Time Air Pollution Monitoring Platform**

## **Project Description**

This project is a real-time air pollution monitoring and analysis platform. It collects, analyzes, and visualizes air quality data from around the world. Users can track air pollution levels in real time, view pollution levels on a map based on geographical regions, and detect potential anomalies.

Air pollution is a significant environmental issue known for its harmful effects on health. This platform not only provides users with the current state of air quality but also allows historical comparisons for deeper analysis. Key air quality parameters such as PM2.5 and PM10 are visualized to keep users informed. The platform includes an infrastructure for collecting and processing real-time data and features an integrated alert system to notify users of anomalies.

---

## **Core Features**

* **Real-Time Air Quality Data Collection**
* **Anomaly Detection and User Alert System**
* **Geographical Data Visualization**
* **Historical Data Analysis**

---

## **System Architecture**

The system is developed using a monolithic architecture for rapid development and management. However, it can be modularized in the future for better scalability. The architecture consists of a Spring Boot-based backend, a React-based frontend, TimescaleDB as the time-series database, and Apache Kafka for managing data streams.

---

## **Technologies Used**

* **Java (Spring Boot):** For rapid development with monolithic structure.
* **Apache Kafka:** For handling high-volume real-time data.
* **TimescaleDB:** For powerful time-series querying and performance.
* **React:** For component-based modern SPA experience.
* **Mapbox:** To display sensor locations on an interactive map.
* **Chart.js:** For visualizing air quality data.
* **Docker:** To containerize and run all components.
* **Axios:** For REST API calls.
* **Shell Scripts (.sh):** For setup and automated data testing.
* **Postman:** For checking the functionality of each component.

---

## **Installation Steps**

### **1. System Requirements**

* [Node.js](https://nodejs.org/en/) (v18.x or later)
* [Java 17+](https://adoptopenjdk.net/)
* [PostgreSQL 15+](https://www.postgresql.org/)
* [Docker and Docker Compose](https://www.docker.com/products/docker-desktop)

### **Database Setup**

```sql
CREATE DATABASE air_quality;
```

### **Database Connection:**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/air_quality
spring.datasource.username=root
spring.datasource.password=yourpassword
```

### **Backend:**

```bash
./mvnw clean install
./mvnw spring-boot:run
```

### **Frontend:**

You can create the React app using IntelliJ or any terminal:

```bash
cd frontend
npm install -g npm@latest
npx create-react-app .
```

### **.env File**

Use this file to store environment variables and secret keys:

```env
REACT_APP_MAPBOX_TOKEN=your-mapbox-token
REACT_APP_API_BASE_URL=http://localhost:8080
```

### **Dockerize:**

```bash
docker-compose up --build
```

---

## **User Guide**

* The app opens with a minimal landing screen.
* It includes information about the app’s purpose.
* Air pollution metrics are displayed on the dashboard.
* A heatmap visualized via Mapbox shows pollution intensity.
* Clicking on points displays data like PM2.5 and PM10 using Chart.js.
* The anomaly panel shows detected anomalies on the map.

---

## **API Parameter Definitions**

* `location`: City name to query air quality (e.g., "Istanbul")
* `lat` and `lon`: Used to query air quality for a specific coordinate.
* `radiusKm`: Radius in kilometers for regional air quality data.
* `startDate` and `endDate`: ISO-format date range for historical queries.

---

## **API Documentation (Spring Boot)**

### **POST Add Air Quality Data:**

```
http://localhost:8080/api/quality/save
```

```json
{
  "id": 11,
  "pm25": 290.0,
  "pm10": 410.0,
  "no2": 15.0,
  "so2": 5.0,
  "o3": 300.0,
  "location": "Izmir",
  "timestamp": "2025-05-04T11:53:35.557468Z",
  "date": null,
  "latitude": 38.925533,
  "longitude": 31.866287
}
```

### **GET All Air Quality Data:**

```
http://localhost:8080/api/quality/all
```

### **GET Air Quality by Location:**

```
http://localhost:8080/api/quality/location?location=Istanbul
```

### **GET Anomalies:**

```
http://localhost:8080/api/quality/regional-anomalies?lat=39.925533&lon=31.866287&radiusKm=25
```

### **GET Air Quality by Location and Date:**

```
http://localhost:8080/api/quality/anomaly/Izmir?startDate=2025-05-04T00:00:00Z&endDate=2025-05-04T23:59:59Z
```

### **GET Last 24 Hours of Air Quality for a Location:**

```
http://localhost:8080/api/quality/last-24-hours/Izmir
```

### **GET Air Quality by Coordinates:**

```
http://localhost:8080/api/quality/region/pollution?latitude=38.925533&longitude=31.866287
```

### **GET Heatmap:**

```
http://localhost:8080/api/quality/heatmap?minLat=35.0&maxLat=42.0&minLon=26.0&maxLon=45.0&zoomLevel=5
```

---

## **Shell Scripts**

### **scripts/auto-test.sh**

```bash
./auto-test.sh --duration=60 --rate=3 --anomaly-chance=30
```

### **scripts/manual-test.sh**

```bash
./manual-input.sh 40.7128 -74.0060 pm25 10
```

---

## **Troubleshooting Guide**

| Issue                         | Solution                                                         |
| ----------------------------- | ---------------------------------------------------------------- |
| `CORS error`                  | Add `@CrossOrigin` or a global CORS config in Spring Boot.       |
| `Mapbox not loading`          | Check if `REACT_APP_MAPBOX_TOKEN` is defined in the `.env` file. |
| `PostgreSQL connection error` | Make sure credentials are correct and port 5432 is open.         |
| `npm run dev fails`           | Check Node version. Try `rm -rf node_modules && npm install`.    |
| Docker container fails        | Try `docker-compose down -v && docker-compose up --build` again. |

---

## **Developer Notes**

* Get a free **Mapbox token** from: [https://account.mapbox.com/access-tokens](https://account.mapbox.com/access-tokens)
* Responsive settings for **Chart.js** are defined in `/components/Charts.jsx`
* API calls are centrally handled in `api/axiosInstance.js`

---

## **Contributing**

To contribute:

1. Fork the repository and create a new branch:

   ```bash
   git checkout -b feature-xyz
   ```
2. Make your changes and commit them:

   ```bash
   git commit -am 'Add new feature xyz'
   ```
3. Push your changes and open a pull request.

---

## **License**

### **MIT License © 2025**

