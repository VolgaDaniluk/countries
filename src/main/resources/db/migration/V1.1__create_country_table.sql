create table country
(
    id         SERIAL PRIMARY KEY NOT NULL,
    name       varchar not null,
    area       bigint  not null,
    population bigint  not null,
    created_at timestamp default now(),
    updated_at timestamp default now()
);

create index country_name_index
    on country (name);
