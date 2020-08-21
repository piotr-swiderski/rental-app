create table revinfo (
    rev  integer primary key,
    revtstmp bigserial
);

create table car_table_aud (
    id  bigserial,
    rev integer,
    revtype smallint,
    brand varchar(50),
    model_name varchar(50),
    model_version varchar(50),
    colour varchar(50),
    cost float,
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
    name varchar(50),
    surname varchar(50),
    email varchar(50),
    city varchar(50),
    street varchar(50),
    zip_code varchar(50),
    phone varchar(50),
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
