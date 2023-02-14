CREATE TABLE IF NOT EXISTS employee
(
   id BIGSERIAL PRIMARY KEY,
   username VARCHAR(25) UNIQUE NOT NULL,
   password VARCHAR(50) NOT NULL,
   is_admin BOOLEAN NOT NULL
  
);

CREATE TABLE IF NOT EXISTS vehicle
(
   id BIGSERIAL PRIMARY KEY,
   picture VARCHAR(300) NOT NULL,
   numberplate VARCHAR(10) UNIQUE NOT NULL,
   brand VARCHAR(50) NOT NULL,
   model VARCHAR(50) NOT NULL,
   vehicle_status VARCHAR(20) NOT NULL,
   category VARCHAR(25) NOT NULL,
   seat_capacity SMALLINT NOT NULL
);

CREATE TABLE IF NOT EXISTS booking_vehicle
(
   id BIGSERIAL PRIMARY KEY,
   departure TIMESTAMP NOT NULL,
   arrival TIMESTAMP,
   id_vehicle INT NOT NULL,
   id_employee INT NOT NULL,
   FOREIGN KEY(id_vehicle) REFERENCES vehicle(id),
   FOREIGN KEY(id_employee) REFERENCES employee(id)
);


CREATE TABLE IF NOT EXISTS advert_carpooling
(
   id BIGSERIAL PRIMARY KEY,
   departure TIMESTAMP NOT NULL,
   departure_adress VARCHAR(50) NOT NULL,
   arrival_adress VARCHAR(50) NOT NULL,
   seat_available SMALLINT NOT NULL,
   id_employee INT NOT NULL,
   FOREIGN KEY(id_employee) REFERENCES employee(id)
);

CREATE TABLE IF NOT EXISTS booking_advert_carpooling
(
   id BIGSERIAL PRIMARY KEY,
   id_advert_carpooling INT NOT NULL,
   id_employee INT NOT NULL,
   FOREIGN KEY(id_advert_carpooling) REFERENCES advert_carpooling(id),
   FOREIGN KEY(id_employee) REFERENCES employee(id)
);