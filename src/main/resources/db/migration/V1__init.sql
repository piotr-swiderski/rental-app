create table car_table (
    car_id  bigserial not null,
    brand varchar(50) not null,
   -- model varchar(50) not null,
   -- model_version varchar(50) not null,
    colour varchar(50),
    cost float not null,
    engine_type integer,
    production_year integer,
    primary key (car_id)
);

create table client_table (
    client_id  bigserial not null,
    name varchar(50) not null,
    surname varchar(50) not null,
    email varchar(50) not null,
    address varchar(50) not null,
    phone varchar(50) not null,
    primary key (client_id)
);

create table rental_table (
    rental_id bigserial not null,
    car_id bigserial  references car_table(car_id),
    client_id bigserial references client_table(client_id),
    date_rented_begin date,
    date_rented_end date,
    primary key(rental_id)
);
