
create table revision_info (
    id  bigserial primary key,
    last_revision integer not null,
    created_date timestamp,
    modified_date timestamp
);

insert into revision_info (id, last_revision, created_date, modified_date) values ('1', '0', null, null);
