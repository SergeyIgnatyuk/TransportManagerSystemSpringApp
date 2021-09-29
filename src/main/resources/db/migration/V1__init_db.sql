CREATE TABLE trucks
(
    id int8 generated always as identity,
    name varchar(25),
    registration_number varchar (10),

    primary key (id)
);

CREATE TABLE drivers
(
    id int8 generated always as identity,
    full_name varchar(30),
    truck_id int8 UNIQUE,

    foreign key (truck_id) references trucks (id),
    primary key (id)
);

