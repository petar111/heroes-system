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


--changeset petar:2
alter table company add column lead_hero_id bigint;

create table hero_in_company (
    id bigserial not null,
    hero_id bigint not null,
    company_id bigint not null,
    primary key (id)
);

alter table hero_in_company add constraint FK_COMPANY_1
foreign key (company_id) references company (id);

create table creature_in_company (
    id bigserial not null,
    creature_id bigint not null,
    company_id bigint not null,
    amount bigint,
    primary key (id)
);

alter table creature_in_company add constraint FK_COMPANY_1
foreign key (company_id) references company (id);
