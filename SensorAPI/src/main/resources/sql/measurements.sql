CREATE TABLE measurements
(
    id SERIAL PRIMARY KEY,
    temperature decimal NOT NULL,
    raining boolean NOT NULL,
    sensor_id SERIAL REFERENCES udemy_sensors.public.sensors,
    created_at timestamp
);

