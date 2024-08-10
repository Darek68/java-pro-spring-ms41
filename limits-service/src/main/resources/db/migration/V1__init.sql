create table limits
(
    id          bigserial primary key,
    client_id   bigint,
    balance     numeric(7, 2),
    created_at  timestamp,
    updated_at  timestamp
);

insert into limits (client_id, balance)
values (1, 10000.00);