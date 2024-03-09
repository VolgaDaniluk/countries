create table city
(
    id         SERIAL PRIMARY KEY NOT NULL,
    name       varchar not null,
    area       bigint  not null,
    population bigint  not null,
    created_at timestamp default now(),
    updated_at timestamp default now(),
    country_id bigint  not null,
        constraint city_country_fk foreign key (country_id)
            references country (id)
);

create index city_name_index
    on city (name);
