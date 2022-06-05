DROP TABLE IF EXISTS calendar CASCADE;

CREATE TABLE calendar
(
    id   bigint      NOT NULL,
    data date        NOT NULL,
    name varchar(20) NOT NULL,
    PRIMARY KEY (id)
);
