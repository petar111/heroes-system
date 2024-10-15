--liquibase formatted sql

--changeset petar:1
create table battle_type (
        date_created timestamp(6),
        date_last_updated timestamp(6),
        id bigserial not null,
        description varchar(255),
        name varchar(255) not null unique,
        primary key (id)
);

--changeset petar:2
create table battle_capacity(
        id bigserial not null,
        attack bigint not null,
        defence bigint not null,
        battle_type_id bigint not null,
        entity_id bigint not null,
        primary key (id)
);

alter table battle_capacity add constraint FK_BATTLE_TYPE_1
foreign key (battle_type_id) references battle_type (id);


--changeset petar:3
alter table battle_capacity add column attack_min bigint;
alter table battle_capacity add column attack_max bigint;
alter table battle_capacity add column defence_min bigint;
alter table battle_capacity add column defence_max bigint;

update battle_capacity set attack_min = attack;
update battle_capacity set attack_max = attack;

update battle_capacity set defence_min = defence;
update battle_capacity set defence_max = defence;

alter table battle_capacity alter column attack_min set not null;
alter table battle_capacity alter column attack_max set not null;
alter table battle_capacity alter column defence_min set not null;
alter table battle_capacity alter column defence_max set not null;

alter table battle_capacity drop column attack;
alter table battle_capacity drop column defence;
