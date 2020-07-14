create table permission (
  id bigserial not null,
  name varchar(512) default null,
  primary key (id),
  unique(name)
) ;

create table role (
  id bigserial not null,
  name varchar(255) default null,
  primary key (id),
  unique(name)
);

create table user_table (
  id bigserial not null,
  username varchar(100) not null,
  password varchar(1024) not null,
  email varchar(1024) not null,
  enabled boolean default true,
  account_non_expired boolean default true,
  credentials_non_expired boolean default true,
  account_non_locked boolean default true,
  primary key (id),
  unique(username),
  unique(email)
);


create table permission_role (
  permission_id bigserial references permission (id),
  role_id bigserial references role (id),
  primary key(permission_id, role_id)
);

create table role_user (
  role_id bigserial references role(id),
  user_id bigserial references user_table(id),
  primary key(role_id, user_id)
);

