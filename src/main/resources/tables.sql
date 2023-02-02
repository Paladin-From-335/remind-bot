create table remind_table
(
    remind_id   bigserial not null
        constraint remind_table_pk
            primary key,
    remind_text text      not null,
    chat_id     bigint    not null,
    created_at  timestamp not null,
    created_to  timestamp not null
);

alter table remind_table
    owner to postgres;

create table user_table
(
    user_id       bigint            not null
        constraint user_table_pk
            primary key,
    user_timezone text,
    is_turn_on    integer default 1 not null,
    repeats       integer default 3 not null
);

alter table user_table
    owner to postgres;

create unique index user_table_user_id_uindex
    on user_table (user_id);

