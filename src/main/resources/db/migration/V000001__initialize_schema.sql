create table messages
(
  id              uuid                      not null,
  content         text,
  created_at      timestamp                 not null,
  primary key (id)
);

create unique index if not exists idx_messages_id
  on messages (id);