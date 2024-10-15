--liquibase formatted sql

--changeset petar:1
create table trait (
        date_created timestamp(6),
        date_last_updated timestamp(6),
        id bigserial not null,
        description varchar(255),
        name varchar(255) not null unique,
        primary key (id)
);

--changeset petar:2
create table maturity(
        date_created timestamp(6),
        date_last_updated timestamp(6),
        id bigserial not null,
        description varchar(255),
        name varchar(255) not null unique,
        primary key (id)
);

create table trait_for_hero (
        id bigserial not null,
        hero_id bigint not null,
        trait_id bigint not null,
        maturity_id bigint not null,
        primary key (id)
);
