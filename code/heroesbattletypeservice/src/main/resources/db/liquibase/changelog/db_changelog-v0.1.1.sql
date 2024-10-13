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

