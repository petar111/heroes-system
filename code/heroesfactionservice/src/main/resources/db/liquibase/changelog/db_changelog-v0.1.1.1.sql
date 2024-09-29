--liquibase formatted sql

--changeset petar:2.1
insert into faction (id, description, name) values (1, 'Nature', 'Nature');
insert into faction_version (id, description, name, faction_id, version) values (1, 'Nature', 'Nature', 1, 1);

insert into faction (id, description, name) values (2, 'Chaos', 'Chaos');
insert into faction_version (id, description, name, faction_id, version) values (2, 'Chaos', 'Chaos', 2, 1);

insert into faction (id, description, name) values (3, 'Death', 'Death');
insert into faction_version (id, description, name, faction_id, version) values (3, 'Death', 'Death', 3, 1);

insert into faction (id, description, name) values (4, 'Life', 'Life');
insert into faction_version (id, description, name, faction_id, version) values (4, 'Life', 'Life', 4, 1);

insert into faction (id, description, name) values (5, 'Order', 'Order');
insert into faction_version (id, description, name, faction_id, version) values (5, 'Order', 'Order', 5, 1);

insert into faction (id, description, name) values (6, 'Might', 'Might');
insert into faction_version (id, description, name, faction_id, version) values (6, 'Might', 'Might', 6, 1);