CREATE TABLE quality
(
    id        VARCHAR(255) NOT NULL,
    pm25 DOUBLE NOT NULL,
    pm10 DOUBLE NOT NULL,
    no2 DOUBLE NOT NULL,
    so2 DOUBLE NOT NULL,
    o3 DOUBLE NOT NULL,
    location  VARCHAR(255) NULL,
    timestamp timestamp NULL,
    CONSTRAINT `pk_qualıty` PRIMARY KEY (id)
);