CREATE TABLE IF NOT EXISTS employee
(
   id BIGSERIAL PRIMARY KEY,
   username VARCHAR(25) UNIQUE NOT NULL,
   password VARCHAR(50) NOT NULL
);

CREATE TYPE CATEGORY AS ENUM ('MICRO_URBAINE',
    'MINI_CITADINE',
    'CITADINE_POLYVALENTE',
    'COMPACTE',
    'BERLINE_TAILLE_S',
    'BERLINE_TAILLE_M',
    'BERLINE_TAILLE_L',
    'SUV',
    'TOUT_TERRAIN',
    'PICK_UP');

CREATE TYPE STATUS AS ENUM(
   'EN_SERVICE',
   'EN_REPARATION',
   'HORS_SERVICE');

CREATE TABLE IF NOT EXISTS vehicle
(
   id BIGSERIAL PRIMARY KEY,
   picture VARCHAR(300) NOT NULL,
   numberplate VARCHAR(10) UNIQUE NOT NULL,
   brand VARCHAR(50) NOT NULL,
   model VARCHAR(50) NOT NULL,
   status STATUS(20) NOT NULL,
   category CATEGORY(25) NOT NULL,
   seat_capacity SMALLINT NOT NULL
);

CREATE TABLE IF NOT EXISTS booking_vehicle
(
   id BIGSERIAL PRIMARY KEY,
   departure TIMESTAMP NOT NULL,
   arrival TIMESTAMP,
   id_vehicle BIGSERIAL NOT NULL,
   id_employee BIGSERIAL NOT NULL,
   FOREIGN KEY(id_vehicle) REFERENCES vehicle(id),
   FOREIGN KEY(id_employee) REFERENCES employee(id)
);

CREATE TABLE IF NOT EXISTS admin
(
   id BIGSERIAL PRIMARY KEY,
   id_employee INT NOT NULL,
   UNIQUE(id_employee),
   FOREIGN KEY(id_employee) REFERENCES employee(id)
);

CREATE TABLE IF NOT EXISTS advert_carpooling
(
   id BIGSERIAL PRIMARY KEY,
   departure TIMESTAMP NOT NULL,
   departure_adress VARCHAR(50) NOT NULL,
   arrival_adress VARCHAR(50) NOT NULL,
   seat_available SMALLINT NOT NULL,
   id_vehicle BIGSERIAL NOT NULL,
   id_employee BIGSERIAL NOT NULL,
   FOREIGN KEY(id_vehicle) REFERENCES vehicle(id),
   FOREIGN KEY(id_employee) REFERENCES employee(id)
);

CREATE TABLE IF NOT EXISTS booking_advert_carpooling
(
   id BIGSERIAL PRIMARY KEY,
   id_advert_carpooling BIGSERIAL NOT NULL,
   id_employee BIGSERIAL NOT NULL,
   FOREIGN KEY(id_advert_carpooling) REFERENCES advert_carpooling(id),
   FOREIGN KEY(id_employee) REFERENCES employee(id)
);