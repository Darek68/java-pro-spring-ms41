create table clients
(
    id          bigserial primary key,
    first_name  varchar(255),
    middle_name varchar(255),
    last_name   varchar(255),
    created_at  timestamp,
    updated_at  timestamp
);

insert into clients (first_name, middle_name, last_name)
values ('Иван', 'Иванович', 'Иванов');

insert into clients (first_name, middle_name, last_name)
values ('Сергей', 'Сергеевич', 'Сергеев');