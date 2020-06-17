
-- !Ups

CREATE TABLE tasks (
  id            serial          NOT NULL,
  name          varchar(100)    NOT NULL,
  description   text
);

CREATE UNIQUE index task_name_uindex
    ON tasks (name);

CREATE UNIQUE  index task_id_uindex
    ON tasks (id);

ALTER TABLE tasks
    ADD CONSTRAINT task_pk
        PRIMARY KEY (id);

-- !Downs

DROP TABLE IF EXISTS tasks;
