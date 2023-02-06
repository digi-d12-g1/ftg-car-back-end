-- employee table
INSERT INTO employee (username, password)
VALUES
    ('user1', 'pass1'),
    ('user2', 'pass2'),
    ('user3', 'pass3'),
    ('user4', 'pass4'),
    ('admin', 'password');

-- vehicle table
INSERT INTO vehicle (picture, numberplate, brand, model, status, category, seat_capacity)
VALUES
    ('https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/2018_Toyota_Camry_%28ASV70R%29_Ascent_sedan_%282018-08-27%29_01.jpg/1920px-2018_Toyota_Camry_%28ASV70R%29_Ascent_sedan_%282018-08-27%29_01.jpg',
     '111xxx999', 'Toyota', 'Camry Ascent', 'available', 'Sedan', 4),
    ('https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/2021_Toyota_Land_Cruiser_300_3.4_ZX_%28Colombia%29_front_view_04.png/1902px-2021_Toyota_Land_Cruiser_300_3.4_ZX_%28Colombia%29_front_view_04.png',
     '222xxx888', 'Toyota', 'Land Cruiser ZX', 'reserved', 'SUV', 5),
    ('https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/2020_Volkswagen_Golf_Style_1.5_Front.jpg/1920px-2020_Volkswagen_Golf_Style_1.5_Front.jpg',
     '333xxx777', 'Volkswagen', 'Golf Mk8', 'available', 'Hatchback', 5),
    ('https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/2009_Kia_Borrego_LX_--_NHTSA.jpg/1575px-2009_Kia_Borrego_LX_--_NHTSA.jpg',
     '444xxx666', 'Kia', 'Borrego', 'reserved', 'SUV', 5);

-- booking_vehicle table
INSERT INTO booking_vehicle ( departure, arrival, id_vehicle, id_employee)
VALUES
    ('2022-10-01 10:00:00', '2022-10-01 12:00:00', 1, 1),
    ('2022-10-02 09:00:00', '2022-10-02 11:00:00', 2, 2),
    ('2022-10-03 08:00:00', '2022-10-03 10:00:00', 3, 3),
    ('2022-10-04 07:00:00', '2022-10-04 09:00:00', 4, 4);

-- admin table
INSERT INTO admin (id_employee)
VALUES
    (5);

-- advert_carpooling table
INSERT INTO advert_carpooling ( departure, departure_adress, arrival_adress, seat_available, id_vehicle, id_employee)
VALUES
    ('2022-10-01 08:00:00', 'address1', 'address2', 4, 1, 1),
    ('2022-10-02 09:00:00', 'address3', 'address4', 3, 2, 2),
    ('2022-10-03 10:00:00', 'address5', 'address6', 2, 3, 3),
    ('2022-10-04 11:00:00', 'address7', 'address8', 1, 4, 4);

-- booking_advert_carpooling table
INSERT INTO booking_advert_carpooling ( id_advert_carpooling, id_employee)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4);

-- management_vehicle table
INSERT INTO management_vehicle (id_vehicle, id_admin)
VALUES
    (1, 1),
    (2, 1);