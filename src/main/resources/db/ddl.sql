CREATE TABLE air_quality
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    pm25      DOUBLE PRECISION,
    pm10      DOUBLE PRECISION,
    no2       DOUBLE PRECISION,
    so2       DOUBLE PRECISION,
    o3        DOUBLE PRECISION,
    location  VARCHAR(255),
    timestamp TIMESTAMP WITH TIME ZONE,
    date      DATE,
    latitude  DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    CONSTRAINT "pk_air_quality" PRIMARY KEY (id)
);

ALTER TABLE air_quality
    ALTER COLUMN "date" SET DATA TYPE date USING "date"::date;