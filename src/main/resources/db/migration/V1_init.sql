create table car_table (
    car_id  bigserial not null,
    brand varchar(50),
    colour varchar(50),
    cost number,
    engine_type float,
    production_year integer,
    primary key (car_id)
    )

create table client_table (
    client_id  bigserial not null,
    money number default 0,
    email varchar(50) not null,
    name varchar(50) not null,
    surname varchar(50) not null,
    primary key (client_id),
    FOREIGN KEY (car_id) REFERENCES car_table(id) ON DELETE CASCADE,
    FOREIGN KEY (client_id) REFERENCES client_table(id) ON DELETE CASCADE
    )

