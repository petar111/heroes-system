--liquibase formatted sql

--changeset petar:1
create table entity_definition (
        date_created timestamp(6),
        date_last_updated timestamp(6),
        id bigserial not null,
        description varchar(255),
        name varchar(255) not null unique,
        hitpoints bigint,
        primary key (id)
);

create table hero (
    entity_definition_id bigint not null,
    experience bigint not null,
    primary key (entity_definition_id)
);

create table creature (
    entity_definition_id bigint not null,
    primary key (entity_definition_id)
);

alter table hero add constraint FK_ENTITY_DEFINITION_1
foreign key (entity_definition_id) references entity_definition (id)
ON DELETE CASCADE;

alter table creature add constraint FK_ENTITY_DEFINITION_1
foreign key (entity_definition_id) references entity_definition (id)
ON DELETE CASCADE;



--changeset petar:3
alter table entity_definition add column origin_id bigint;

--changeset petar:4
create table _level(
    id bigserial not null,
    number bigint not null,
    experience_lower bigint,
    experience_upper bigint,
    primary key (id)
);

alter table hero add column level_id bigint;

alter table hero add constraint FK_LEVEL_1
foreign key (level_id) references _level (id);
