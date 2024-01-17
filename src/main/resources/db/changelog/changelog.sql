--liquibase formatted sql

--changeset nick:storage

CREATE TABLE STORAGE
(
    ID         bigserial primary key,
    time_created TIMESTAMP,
    time_updated TIMESTAMP,
    NAME      varchar(128) not null

);
--changeset nick:equipment

CREATE TABLE EQUIPMENT
(
    ID         bigserial primary key,
    NAME      varchar(64) not null,
    time_created TIMESTAMP,
    time_updated TIMESTAMP,
    DESCRIPTION   varchar(255)  not null,
    PRICE bigint not null,
    CATEGORY varchar(64) not null,
    storage_id INT NOT NULL,
    FOREIGN KEY (storage_id) REFERENCES STORAGE(id)

);

