create table revinfo (
    rev  integer primary key,
    revtstmp bigserial
);

create table car_table_aud (
    id  bigserial,
    rev integer,
    revtype smallint,
    brand varchar(50) not null,
    model_name varchar(50) not null,
    model_version varchar(50) not null,
    colour varchar(50),
    cost float not null,
    engine_type varchar(50),
    production_year integer,
    primary key (id, rev),
    CONSTRAINT fk_car_table_aud_rev
        FOREIGN KEY(rev)
	    REFERENCES public.revinfo(rev)
);

create table client_table_aud (
    id  bigserial,
    rev integer,
    revtype smallint,
    name varchar(50) not null,
    surname varchar(50) not null,
    email varchar(50) not null,
    city varchar(50) not null,
    street varchar(50) not null,
    zip_code varchar(50) not null,
    phone varchar(50) not null,
    primary key (id, rev),
    CONSTRAINT fk_client_table_aud_rev
        FOREIGN KEY(rev)
	    REFERENCES public.revinfo(rev)
);

CREATE TABLE rental_table_aud (
    id bigserial,
    rev integer,
    revtype smallint,
    client_id bigserial,
    car_id bigserial,
    created_date timestamp,
    modified_date timestamp,
    date_rented_begin date,
    date_rented_end date,
    primary key (id, rev),
    CONSTRAINT fk_rental_table_aud_rev
        FOREIGN KEY(rev)
	    REFERENCES public.revinfo(rev)
);
