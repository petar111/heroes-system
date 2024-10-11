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
        version bigint not null,
        description varchar(255),
        name varchar(255) not null,
        primary key (id)
    );

alter table if exists faction_version 
   add constraint FK1_faction 
   foreign key (faction_id) 
   references faction;

--changeset petar:2.1
alter table if exists faction_version
   drop constraint FK1_faction;

alter table if exists faction_version
   add constraint FK1_faction
   foreign key (faction_id)
   references faction(id) ON DELETE CASCADE;

--changeset petar:2.2
drop table faction_version;
