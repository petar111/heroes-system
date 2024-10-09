--liquibase formatted sql

--changeset petar:1
create table company (
        date_created timestamp(6),
        date_last_updated timestamp(6),
        id bigserial not null,
        faction_id bigint not null,
        description varchar(255),
        name varchar(255) not null unique,
        primary key (id)
);
