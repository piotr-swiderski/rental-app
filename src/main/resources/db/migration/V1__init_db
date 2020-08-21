
create table car_table (
    id  bigserial primary key,
    brand varchar(50) not null,
    model_name varchar(50) not null,
    model_version varchar(50) not null,
    colour varchar(50),
    cost float not null,
    engine_type varchar(50),
    production_year integer,
    created_date timestamp,
    modified_date timestamp
);

create table client_table (
    id  bigserial primary key,
    name varchar(50) not null,
    surname varchar(50) not null,
    email varchar(50) not null,
    city varchar(50) not null,
    street varchar(50) not null,
    zip_code varchar(50) not null,
    phone varchar(50) not null,
    created_date timestamp,
    modified_date timestamp
);

CREATE TABLE rental_table (
    id bigserial primary key,
    client_id bigserial,
    car_id bigserial,
    created_date timestamp,
    modified_date timestamp,
    date_rented_begin date,
    date_rented_end date,
    CONSTRAINT fk_rental_table_client_id
        FOREIGN KEY(client_id)
	    REFERENCES public.client_table(id),
	CONSTRAINT fk_rental_table_car_id
        FOREIGN KEY(car_id)
	    REFERENCES public.car_table(id)
);
