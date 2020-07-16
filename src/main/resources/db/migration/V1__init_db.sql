create table car_table (
    id  bigserial not null,
    brand varchar(50) not null,
    model_name varchar(50) not null,
    model_version varchar(50) not null,
    colour varchar(50),
    cost float not null,
    engine_type varchar(50),
    production_year integer,
    created_date timestamp,
    modified_date timestamp,
    primary key (id)
);

create table client_table (
    id  bigserial not null,
    name varchar(50) not null,
    surname varchar(50) not null,
    email varchar(50) not null,
    city varchar(50) not null,
    street varchar(50) not null,
    zip_code varchar(50) not null,
    phone varchar(50) not null,
    created_date timestamp,
    modified_date timestamp,
    primary key (id)
);

create table rental_table (
    id bigserial not null,
    car_id bigserial  references car_table(id),
    client_id bigserial references client_table(id),
    created_date timestamp,
    modified_date timestamp,
    date_rented_begin date,
    date_rented_end date,
    primary key(id)
);