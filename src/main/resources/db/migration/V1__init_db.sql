CREATE TABLE trucks
(
    id int8 generated always as identity,
    name varchar(25) NOT NULL,
    registration_number varchar (10) NOT NULL,

    primary key (id)
);

CREATE TABLE drivers
(
    id int8 generated always as identity,
    full_name varchar(30) NOT NULL,
    truck_id int8 UNIQUE,

    foreign key (truck_id) references trucks (id),
    primary key (id)
);

INSERT INTO trucks (name, registration_number) VALUES ('DAF FT XF 105.460', 'AK 0595-1');
INSERT INTO trucks (name, registration_number) VALUES ('MAN TGX 18.500', 'AM 7171-1');
INSERT INTO trucks (name, registration_number) VALUES ('Mercedes-Benz Actros 1844', 'AM 3998-1');

INSERT INTO drivers (full_name, truck_id) VALUES ('Иван Иванов', 1);
INSERT INTO drivers (full_name, truck_id) VALUES ('Василий Васильев', 2);
INSERT INTO drivers (full_name, truck_id) VALUES ('Андрей Андреев', 3);
INSERT INTO drivers (full_name, truck_id) VALUES ('Михаил Михайлов', null);