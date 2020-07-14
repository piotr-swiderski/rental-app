insert into permission (id, name) values
(1,'create_profile'),
(2,'read_profile'),
(3,'update_profile'),
(4,'delete_profile');

insert into role (id, name) values
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

insert into permission_role (permission_id, role_id) values
(1,1),
(2,1),
(3,1),
(4,1),
(2,2),
(3,2);

insert into user_table (id, username, password, email) values ('1', 'admin', '{bcrypt}$2a$10$fgGN6ElQWmDXnodqaFZ3ZuNiLs.b2ZDnEifuzNe9x9P2iKd1g4VyO', 'admin@admin.com');
insert into user_table (id, username, password, email) values ('2', 'user', '{bcrypt}$2a$10$zQYiNMJ1oH8.7ELKUrgtYup6.LZpP1StZ8MSnNREo51VG7GM.THJK', 'user@user.com');

insert into role_user (role_id, user_id) values
(1,1),
(2,2);
