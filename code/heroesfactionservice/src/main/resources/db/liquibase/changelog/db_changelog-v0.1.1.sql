--liquibase formatted sql

--changeset petar:1
create table faction (
        date_created timestamp(6),
        date_last_updated timestamp(6),
        id bigserial not null,
        description varchar(255),
        name varchar(255) not null unique,
        primary key (id)
);

--changeset petar:2
create table faction_version (
        date_created timestamp(6),
        id bigserial not null,
        faction_id bigint not null,
        version bigint not null unique,
        description varchar(255),
        name varchar(255) not null unique,
        primary key (id)
    );

alter table if exists faction_version 
   add constraint FK1_faction 
   foreign key (faction_id) 
   references faction;
