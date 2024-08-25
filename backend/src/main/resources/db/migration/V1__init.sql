create table accounts
(
    id             bigserial primary key,
    client_id      bigint,
    account_number varchar(16),
    balance        numeric(6, 2),
    created_at     timestamp,
    updated_at     timestamp
);

insert into accounts (client_id, account_number, balance)
values (1, '1234123412341234', 3000);

insert into accounts (client_id, account_number, balance)
values (2, '1234123412341234', 2000);